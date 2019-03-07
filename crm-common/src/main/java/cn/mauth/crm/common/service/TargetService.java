package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Target;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.common.repository.TargetRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetService extends BaseService<TargetRepository,Target>{

    public TargetService(TargetRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<Target> findAll(Target bean) {
        return repository.findAll(this.specification(bean));
    }

    public Page<Target> page(Target bean,Pageable pageable) {
        return repository.findAll(this.specification(bean),pageable);
    }

    private Specification<Target> specification(Target bean){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(bean.getTeam()))
                list.add(cb.equal(root.get("team"),bean.getTeam()));

            if(bean.getStatus()!=-1)
                list.add(cb.equal(root.get("status"),bean.getStatus()));

            if(StringUtils.isNotEmpty(bean.getCreatorId()))
                list.add(cb.equal(root.get("creatorId"),bean.getCreatorId()));

            if(StringUtils.isNotEmpty(bean.getModifiedId()))
                list.add(cb.equal(root.get("modifiedId"),bean.getModifiedId()));

            if(StringUtils.isNotEmpty(bean.getOwnerId()))
                list.add(cb.equal(root.get("ownerId"),bean.getOwnerId()));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
