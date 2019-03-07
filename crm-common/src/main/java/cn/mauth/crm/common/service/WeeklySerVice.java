package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Weekly;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.common.repository.WeeklyRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeeklySerVice extends BaseService<WeeklyRepository,Weekly>{

    public WeeklySerVice(WeeklyRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<Weekly> findAll(Weekly bean) {
        return repository.findAll(this.specification(bean));
    }

    public Page<Weekly> page(Weekly bean, Pageable pageable) {
        return repository.findAll(this.specification(bean),pageable);
    }

    private Specification<Weekly> specification(Weekly bean){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(bean.getUserName()))
                list.add(cb.equal(root.get("userName"),bean.getUserName()));


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
