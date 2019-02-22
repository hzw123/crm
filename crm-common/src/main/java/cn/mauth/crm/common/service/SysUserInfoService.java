package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserInfoService extends BaseService<SysUserInfoRepository,SysUserInfo>{

    public SysUserInfoService(SysUserInfoRepository repository) {
        super(repository);
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

}
