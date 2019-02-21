package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Client;
import cn.mauth.crm.common.repository.ClientRepository;
import cn.mauth.crm.util.base.BaseService;

import org.springframework.stereotype.Service;


@Service
public class ClientService extends BaseService<ClientRepository,Client>{

    public ClientService(ClientRepository repository) {
        super(repository);
    }


}
