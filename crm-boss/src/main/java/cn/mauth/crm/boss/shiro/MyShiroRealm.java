package cn.mauth.crm.boss.shiro;


import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.util.common.HexUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 身份校验核心类
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger log= LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private SysUserInfoRepository sysUserInfoRepository;

	/**
	 * 认证登陆
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		 //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }

		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		SysUserInfo sysUserInfo = sysUserInfoRepository.findByUserName(username);
		
		log.info("----->>userInfo=" + sysUserInfo.getUserName());

		if(StringUtils.isEmpty(username)){
			throw new UnknownAccountException("没有用户！");
		}

		if (null == sysUserInfo) {
			throw new UnknownAccountException("账号不存在");
		}

		if (!sysUserInfo.getPwd().equals(HexUtil.md5Hex( password+sysUserInfo.getSalt()))) {
			throw new UnknownAccountException("账号或者密码不正确");
		}

		if(sysUserInfo.getStatus()==2){
			throw new LockedAccountException("账户已冻结");
		}

		if(sysUserInfo.getStatus()==3){
			throw new AuthenticationException("账户已注销");
		}

		if(sysUserInfo.isDelete()){
			throw new AuthenticationException("账户已经删除");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				username, // 用户名
				password, // 密码
				this.getName() // realm name
		);

		addLoginLog(sysUserInfo,false);

		return authenticationInfo;

		
	}
	
	 /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		if(principals == null){  
	       throw new AuthorizationException("principals should not be null");  
	    }

		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		return simpleAuthorizationInfo;
	}
	
	 /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

	public static void addLoginLog(SysUserInfo sysUserInfo, boolean flag){

    }

}
