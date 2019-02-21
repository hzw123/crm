package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysRole;
import cn.mauth.crm.common.repository.SysRoleRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;


@Service
public class SysRoleService extends BaseService<SysRoleRepository,SysRole>{

    public SysRoleService(SysRoleRepository repository) {
        super(repository);
    }

}
