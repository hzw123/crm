package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysAuthority;
import cn.mauth.crm.common.repository.SysAuthorityRepository;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysAuthService extends BaseService<SysAuthorityRepository,SysAuthority>{


    public SysAuthService(SysAuthorityRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<SysAuthority> findAll(SysAuthority bean) {
        return repository.findAll(this.specification(bean));
    }

    public Page<SysAuthority> page(SysAuthority bean,Pageable pageable) {
        return repository.findAll(this.specification(bean),pageable);
    }

    private Specification<SysAuthority> specification(SysAuthority bean){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(bean.getName()))
                list.add(cb.equal(root.get("name"),bean.getName()));

            if(StringUtils.isNotEmpty(bean.getPath()))
                list.add(cb.like(root.get("path"),bean.getPath()+"%"));

            if(StringUtils.isNotEmpty(bean.getCreatorId()))
                list.add(cb.equal(root.get("creatorId"),bean.getCreatorId()));

            if(StringUtils.isNotEmpty(bean.getModifiedId()))
                list.add(cb.equal(root.get("modifiedId"),bean.getModifiedId()));

            if(StringUtils.isNotEmpty(bean.getOwnerId()))
                list.add(cb.equal(root.get("ownerId"),bean.getOwnerId()));

            if(bean.getStatus()!=-1)
                list.add(cb.equal(root.get("status"),bean.getStatus()));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
