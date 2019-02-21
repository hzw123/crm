package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Organization;
import cn.mauth.crm.common.repository.OrganizationRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService extends BaseService<OrganizationRepository,Organization>{

    public OrganizationService(OrganizationRepository repository) {
        super(repository);
    }
}
