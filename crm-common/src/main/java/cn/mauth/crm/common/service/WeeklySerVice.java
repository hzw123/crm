package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Weekly;
import cn.mauth.crm.common.repository.WeeklyRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class WeeklySerVice extends BaseService<WeeklyRepository,Weekly>{

    public WeeklySerVice(WeeklyRepository repository) {
        super(repository);
    }
}
