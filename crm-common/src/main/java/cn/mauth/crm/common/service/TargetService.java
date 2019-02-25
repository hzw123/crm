package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Target;
import cn.mauth.crm.common.repository.TargetRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetService extends BaseService<TargetRepository,Target>{

    public TargetService(TargetRepository repository) {
        super(repository);
    }

    public List<Target> findAll() {
        return super.findAll();
    }

    @Override
    public Page<Target> page(Pageable pageable) {
        return super.page(pageable);
    }

    private Specification<Target> specification(){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
