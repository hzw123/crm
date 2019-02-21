package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Visit;
import cn.mauth.crm.common.repository.VisitRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class VisitService extends BaseService<VisitRepository,Visit>{

    public VisitService(VisitRepository repository) {
        super(repository);
    }
}
