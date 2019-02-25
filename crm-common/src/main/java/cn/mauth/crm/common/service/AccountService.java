package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Account;
import cn.mauth.crm.common.repository.AccountRepository;
import cn.mauth.crm.util.base.BaseService;

import org.springframework.stereotype.Service;


@Service
public class AccountService extends BaseService<AccountRepository,Account>{

    public AccountService(AccountRepository repository) {
        super(repository);
    }


}
