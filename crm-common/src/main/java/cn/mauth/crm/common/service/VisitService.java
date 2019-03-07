package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Visit;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.common.repository.VisitRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService extends BaseService<VisitRepository,Visit>{

    public VisitService(VisitRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<Visit> findAll(Visit bean) {
        return repository.findAll(this.specification(bean));
    }

    public Page<Visit> page(Visit bean, Pageable pageable) {
        return repository.findAll(this.specification(bean),pageable);
    }

    private Specification<Visit> specification(Visit bean){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(bean.getTerminalName()))
                list.add(cb.equal(root.get("terminalName"),bean.getTerminalName()));

            if(StringUtils.isNotEmpty(bean.getTerminalAddress()))
                list.add(cb.equal(root.get("terminalAddress"),bean.getTerminalAddress()));

            if(bean.getAccountId()!=null && bean.getAccountId()>0)
                list.add(cb.equal(root.get("accountId"),bean.getAccountId()));

            if(bean.getContactId()!=null && bean.getContactId()>0)
                list.add(cb.equal(root.get("contactId"),bean.getContactId()));

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
