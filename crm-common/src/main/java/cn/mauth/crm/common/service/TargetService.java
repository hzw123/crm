package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Target;
import cn.mauth.crm.common.repository.TargetRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class TargetService extends BaseService<TargetRepository,Target>{

    public TargetService(TargetRepository repository) {
        super(repository);
    }
}
