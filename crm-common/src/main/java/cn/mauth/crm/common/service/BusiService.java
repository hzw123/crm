package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.BusRecord;
import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.domain.Stage;
import cn.mauth.crm.common.repository.BusRecordRepository;
import cn.mauth.crm.common.repository.BusinessOpportunityRepository;
import cn.mauth.crm.util.base.BaseService;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusiService extends BaseService<BusinessOpportunityRepository,BusinessOpportunity>{

    @Autowired
    private BusRecordRepository busRecordRepository;

    public BusiService(BusinessOpportunityRepository repository) {
        super(repository);
    }

    @Transactional
    public boolean updateStatus(Long id,Integer status){
        boolean flag=false;
        try {
            repository.updateStatus(id,status);
            flag=true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return flag;
    }


    /**
     * 阶段变化
     * @param id
     * @param amount
     * @param stage
     * @return
     */
    public boolean change(Long id,double amount, Stage stage){
        boolean flag=false;
        try{
            BusinessOpportunity businessOpportunity=this.findById(id);
            if(businessOpportunity!=null){

                this.addRecord(businessOpportunity);

                if(amount>0){
                    businessOpportunity.setAmount(amount);
                }

                if(stage!=null){
                    businessOpportunity.setStage(stage);
                }

                repository.save(businessOpportunity);

                flag=true;
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return flag;
    }

    /**
     *添加商机阶段变动记录
     * @param businessOpportunity
     */
    private void addRecord(BusinessOpportunity businessOpportunity){

        BusRecord busRecord=new BusRecord();

        busRecord.setBusId(businessOpportunity.getId());

        busRecord.setSort(businessOpportunity.getStage().getSort());

        busRecord.setStage(businessOpportunity.getStage().getName());

        busRecord.setAmount(businessOpportunity.getAmount());

        busRecord.setBusId(businessOpportunity.getStage().getId());

        busRecordRepository.save(busRecord);
    }


    public Page<BusinessOpportunity> page(Long accountId,int status,String state,Pageable pageable) {
        return repository.findAll(this.specification(accountId,status,state), PageUtil.getPageable(pageable));
    }

    public List<BusinessOpportunity> findAll(Long accountId,int status,String state) {
        return repository.findAll(this.specification(accountId,status,state));
    }

    private Specification<BusinessOpportunity> specification(Long accountId,int status,String state){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(accountId!=null&&accountId>=0)
                list.add(cb.equal(root.get("accountId"),accountId));

            list.add(cb.equal(root.get("status"),status));

            if(StringUtils.isNotEmpty(state))
                list.add(cb.equal(root.join("state").get("name"),state));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
