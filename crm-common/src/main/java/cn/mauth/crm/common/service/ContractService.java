package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.common.repository.ContractRepository;
import cn.mauth.crm.util.base.BaseService;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService extends BaseService<ContractRepository,Contract>{

    public ContractService(ContractRepository repository) {
        super(repository);
    }

    public List<Contract> findAll(
            String name,String code,String type,
            String title, Long accountId,Long busId) {
        return repository.findAll(this.specification(name, code, type, title, accountId, busId));
    }

    public Page<Contract> page(
            String name,String code,String type, String title,
            Long accountId,Long busId,Pageable pageable) {
        return repository.findAll(
                this.specification(name, code, type, title, accountId, busId),
                PageUtil.getPageable(pageable)
        );
    }

    private Specification<Contract> specification(
            String name,String code,String type,
            String title,Long accountId,Long busId){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(name))
                list.add(cb.equal(root.get("name"),name));

            if(StringUtils.isNotEmpty(code))
                list.add(cb.equal(root.get("code"),code));

            if(StringUtils.isNotEmpty(type))
                list.add(cb.equal(root.get("type"),type));

            if(StringUtils.isNotEmpty(title))
                list.add(cb.equal(root.get("title"),title));

            if(accountId!=null && accountId>0)
                list.add(cb.equal(root.get("accountId"),accountId));

            if(busId!=null && busId>0)
                list.add(cb.equal(root.get("busId"),busId));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
