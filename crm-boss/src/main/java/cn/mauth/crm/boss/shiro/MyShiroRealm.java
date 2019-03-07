package cn.mauth.crm.boss.shiro;

import cn.mauth.crm.common.domain.SysLoginLog;
import cn.mauth.crm.common.domain.SysRole;
import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.common.service.SysLoginLogSerVice;
import cn.mauth.crm.common.service.SysUserInfoService;
import cn.mauth.crm.util.common.*;
import cn.mauth.crm.util.enums.LoginEnum;
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

import java.util.Set;

/**
 * 身份校验核心类
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger log= LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private SysUserInfoService sysUserInfoService;

	@Autowired
	private SysLoginLogSerVice sysLoginLogSerVice;

	/**
	 * 认证登陆
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

		if (authenticationToken.getPrincipal() == null) {
			return null;
		}

		UserToken token=(UserToken) authenticationToken;


		LoginEnum type=token.getLoginType();

		String username = token.getUsername();

		String password =new String((char[]) token.getCredentials());

		if(StringUtils.isEmpty(username)){
			throw new UnknownAccountException("openId为空！");
		}

		SysUserInfo sysUserInfo=null;

		if(type.getCode()==LoginEnum.PHONE.getCode()){
			sysUserInfo=sysUserInfoService.findByPhone(username);
		}else{
			sysUserInfo = sysUserInfoService.findByWxOpenId(username);
		}


		if (null == sysUserInfo) {
			throw new UnknownAccountException("账号不存在");
		}

		if(type.getCode()==LoginEnum.PHONE.getCode()){
			if (!sysUserInfo.getPassword().equals(HexUtil.md5Hex( sysUserInfo.getSalt()+password))) {
				throw new UnknownAccountException("密码不正确");
			}
		}

		if(sysUserInfo.getStatus()==2){
			throw new LockedAccountException("账户已冻结");
		}

		if(sysUserInfo.getStatus()==3){
			throw new AuthenticationException("账号已注销");
		}

		if(sysUserInfo.isDisabled()){
			throw new AuthenticationException("账号已经删除");
		}

		Set<SysRole> roles=sysUserInfo.getSysRoles();

//		if(roles==null||roles.size()==0){
//			throw new AuthenticationException("请添加角色");
//		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				username, // 用户名
				password, // 密码
				this.getName() // realm name
		);


		SessionUtil.saveUser(sysUserInfo);

		SessionUtil.saveUserId(sysUserInfo.getId());

		SessionUtil.saveRoles(sysUserInfo.getSysRoles());

		SessionUtil.saveLoginType(token.getLoginType().getName());

		boolean flag=false;
		for (SysRole role: roles) {
			if(role.getName().equals("admin")){
				flag=true;
				break;
			}
		}

		SessionUtil.saveAdmin(flag);

		this.addLoginLog(sysUserInfo,token.getLoginType());

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

	private void addLoginLog(SysUserInfo sysUserInfo,LoginEnum type){

		SysLoginLog sysLoginLog=new SysLoginLog();

		sysLoginLog.setIp(IpUtil.getIp(HttpUtil.getRequest()));

		sysLoginLog.setOpenId(sysUserInfo.getWxOpenId());

		sysLoginLog.setLoginType(type);

		sysLoginLog.setSessionId(SessionUtil.getSession().getId().toString());

		sysLoginLogSerVice.add(sysLoginLog);
	}

}
