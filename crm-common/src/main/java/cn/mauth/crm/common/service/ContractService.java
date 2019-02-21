package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.common.repository.ContractRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends BaseService<ContractRepository,Contract>{

    public ContractService(ContractRepository repository) {
        super(repository);
    }
}
