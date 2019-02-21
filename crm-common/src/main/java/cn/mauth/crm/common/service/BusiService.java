package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.BusRecord;
import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.domain.Stage;
import cn.mauth.crm.common.repository.BusRecordRepository;
import cn.mauth.crm.common.repository.BusinessOpportunityRepository;
import cn.mauth.crm.util.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


}
