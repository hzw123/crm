package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.AccountStats;
import cn.mauth.crm.common.domain.Account;
import cn.mauth.crm.common.repository.*;
import cn.mauth.crm.util.base.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public AccountService(AccountRepository repository) {
        super(repository);
    }


    public AccountStats accountStats(Long accountId){
        int contractTotal=contractRepository.countByAccountId(accountId);

        int busTotal=businessOpportunityRepository.countByAccountId(accountId);

        int contactTotal=contactRepository.countByAccountId(accountId);

        int visitTotal=visitRepository.countByAccountId(accountId);

        return new AccountStats(accountId,contractTotal,busTotal,contactTotal,visitTotal);
    }
}
