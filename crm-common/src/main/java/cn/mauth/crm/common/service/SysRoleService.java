package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.Role;
import cn.mauth.crm.common.domain.SysRole;
import cn.mauth.crm.common.repository.SysAuthorityRepository;
import cn.mauth.crm.common.repository.SysRoleRepository;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class SysRoleService extends BaseService<SysRoleRepository,SysRole>{

    @Autowired
    private SysAuthorityRepository sysAuthorityRepository;

    public SysRoleService(SysRoleRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<SysRole> findAll(Role role) {
        return repository.findAll(this.specification(role));
    }

    public Page<SysRole> page(Role role,Pageable pageable) {
        return repository.findAll(this.specification(role),PageUtil.getPageable(pageable));
    }

    private Specification<SysRole> specification(Role bean){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(bean.getName()))
                list.add(cb.equal(root.get("name"),bean.getName()));

            if(bean.getStatus()!=-1)
                list.add(cb.equal(root.get("status"),bean.getStatus()));

            if(StringUtils.isNotEmpty(bean.getCreatorId()))
                list.add(cb.equal(root.get("creatorId"),bean.getCreatorId()));

            if(StringUtils.isNotEmpty(bean.getModifiedId()))
                list.add(cb.equal(root.get("modifiedId"),bean.getModifiedId()));

            if(StringUtils.isNotEmpty(bean.getOwnerId()))
                list.add(cb.equal(root.get("ownerId"),bean.getOwnerId()));

            if(StringUtils.isNotEmpty(bean.getAuth()))
                list.add(cb.in(root.join("sysAuthorities").get("id")).value(bean.getAuth()));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }

    public boolean add(Role role) {
        return super.add(this.toSysRole(role));
    }

    public boolean update(Role role) {
        return super.update(this.toSysRole(role));
    }

    private SysRole toSysRole(Role role){
        SysRole sysRole=new SysRole();

        BeanUtils.copyProperties(role,sysRole);

        if(StringUtils.isNotEmpty(role.getAuth())){
            sysRole.setSysAuthorities(new HashSet<>(
                    sysAuthorityRepository.findByIds(role.getAuth())));
        }

        return sysRole;
    }
}
