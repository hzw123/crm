package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.AccountStats;
import cn.mauth.crm.common.domain.Account;
import cn.mauth.crm.common.repository.*;

import cn.mauth.crm.util.common.NumUtil;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService extends BaseService<AccountRepository,Account>{

    @Autowired
    private BusinessOpportunityRepository businessOpportunityRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private VisitRepository visitRepository;

    public AccountService(AccountRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
    }

    public List<Account> findAll(Account account) {
        return repository.findAll(this.specification(account));
    }

    public Page<Account> page(Account account,Pageable pageable) {
        return repository.findAll(this.specification(account), PageUtil.getPageable(pageable));
    }

    private Specification<Account> specification(Account bean){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(bean.getName()))
                list.add(cb.equal(root.get("name"),bean.getName()));

            if(StringUtils.isNotEmpty(bean.getCreatorId()))
                list.add(cb.equal(root.get("creatorId"),bean.getCreatorId()));

            if(StringUtils.isNotEmpty(bean.getModifiedId()))
                list.add(cb.equal(root.get("modifiedId"),bean.getModifiedId()));

            if(StringUtils.isNotEmpty(bean.getOwnerId()))
                list.add(cb.equal(root.get("ownerId"),bean.getOwnerId()));

            list.add(cb.equal(root.get("isCommon"),bean.isCommon()));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }


    public AccountStats accountStats(Long accountId){
        int contractTotal=contractRepository.countByAccountId(accountId);

        int busTotal=businessOpportunityRepository.countByAccountId(accountId);

        int contactTotal=contactRepository.countByAccountId(accountId);

        int visitTotal=visitRepository.countByAccountId(accountId);

        return new AccountStats(accountId,contractTotal,busTotal,contactTotal,visitTotal);
    }
}
