package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysAuthority;
import cn.mauth.crm.common.repository.SysAuthorityRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SysAuthService extends BaseService<SysAuthorityRepository,SysAuthority>{

    public SysAuthService(SysAuthorityRepository repository) {
        super(repository);
    }
}
