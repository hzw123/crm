package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.UserInfo;
import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.common.repository.SysRoleRepository;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.util.common.*;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class SysUserInfoService extends BaseService<SysUserInfoRepository,SysUserInfo>{

    @Autowired
    private SysRoleRepository sysRoleRepository;

    public SysUserInfoService(SysUserInfoRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public SysUserInfo save(SysUserInfo sysUserInfo) {
        return repository.save(sysUserInfo);
    }


    public Page<SysUserInfo> page(Pageable pageable) {
        return repository.findAll(((root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            list.add(cb.equal(root.get("disabled"),false));

            return cb.and(list.toArray(new Predicate[list.size()]));
        }),this.getPageAble(pageable));
    }

    public List<SysUserInfo> findAdminList(String type) {
        return repository.findAdminList(type);
    }


    @Override
    public List<SysUserInfo> findAll() {
        return repository.findAll((root, query, cb) -> {
            return cb.equal(root.get("disabled"),false);
        });
    }

    @Transactional
    public boolean noDisabled(Long id){
        boolean flag=false;
        try {
            repository.noDisabled(id);
            flag=true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return flag;
    }

    public SysUserInfo findByWxOpenId(String openId){
        return repository.findByWxOpenId(openId);
    }

    public boolean existByWxOpenId(String openId){
        return repository.countByWxOpenId(openId)>0;
    }


    public SysUserInfo findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

//    /**
//     * 手机号登录，手机号，密码
//     * @param token
//     * @return
//     */
//    public Result login(LoginToken token){
//
//        if(StringUtils.isEmpty(token.getPhone())){
//            return Result.error("请输入登录手机号");
//        }
//
//        if(StringUtils.isEmpty(token.getPassword())){
//            return Result.error("请输入登录密码");
//        }
//
//        SysUserInfo user=repository.findByPhone(token.getPhone());
//
//        if(user==null){
//            return Result.error("没有这个用户");
//        }
//
//        if(!user.getPassword().equals(HexUtil.md5Hex(user.getSalt()+token.getPassword()))){
//            return Result.error("密码输入错误");
//        }
//
//        SessionInfo sessionInfo=new SessionInfo();
//
//        sessionInfo.setUser(user);
//
//        sessionInfo.setUserId(user.getId());
//
//        for (SysRole role:user.getSysRoles()) {
//            if(role.getName().equals(Constants.ADMIN)){
//                sessionInfo.setAdmin(true);
//                break;
//            }
//        }
//
//
//        String sessionId = RandomStringUtils.randomAlphanumeric(64);
//
//        redisService.add(sessionId, JSON.toJSONString(sessionInfo));
//
//        this.addLog(user,sessionId);
//
//        return Result.success(sessionId);
//    }

//    /***
//     * 添加日志
//     * @param user
//     * @param sessionId
//     */
//    private void addLog(SysUserInfo user,String sessionId){
//        SysLoginLog sysLoginLog=new SysLoginLog();
//
//        sysLoginLog.setLoginType(LoginEnum.PHONE);
//
//        sysLoginLog.setUserId(user.getId());
//
//        sysLoginLog.setOpenId(user.getWxOpenId());
//
//        sysLoginLog.setIp(IpUtil.getIp(HttpUtil.getRequest()));
//
//        sysLoginLog.setSessionId(sessionId);
//
//        sysLoginLogRepository.save(sysLoginLog);
//    }


    /**
     * 创建用户
     * @return
     */
    public Result createUser(UserInfo user){

        user.setSalt(RandomStringUtils.randomAlphanumeric(32));

        user.setPassword(HexUtil.md5Hex(user.getSalt()+user.getPassword()));

        repository.save(this.toSysUserInfo(user));

        return Result.success("创建成功");
    }

    public boolean update(UserInfo userInfo) {
        return super.update(this.toSysUserInfo(userInfo));
    }

    private SysUserInfo toSysUserInfo(UserInfo userInfo){

        SysUserInfo sysUserInfo=new SysUserInfo();

        BeanUtils.copyProperties(userInfo,sysUserInfo);

        if(StringUtils.isNotEmpty(userInfo.getRoles())){
            sysUserInfo.setSysRoles(new HashSet<>(sysRoleRepository.findByIds(userInfo.getRoles())));
        }

        return sysUserInfo;
    }
}
