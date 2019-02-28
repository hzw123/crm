package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.BusStats;
import cn.mauth.crm.common.domain.BusRecord;
import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.repository.BusRecordRepository;
import cn.mauth.crm.common.repository.BusinessOpportunityRepository;
import cn.mauth.crm.util.base.BaseService;
import cn.mauth.crm.util.common.DateUtil;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

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

    @Override
    public boolean update(BusinessOpportunity businessOpportunity) {
        boolean flag=false;
        try {

            BusinessOpportunity old=this.findById(businessOpportunity.getId());

            if(old!=null){

                businessOpportunity.setCreateAt(old.getCreateAt());

                if(businessOpportunity.getStage()==null){
                    businessOpportunity.setStage(old.getStage());
                }else{
                    if(businessOpportunity.getStage().getId()==old.getStage().getId()){
                        this.addRecord(businessOpportunity);
                    }
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

    public List<BusRecord> findRecord(Long busId){
        return busRecordRepository.findAll(this.recordParam(busId));
    }

    public Page<BusRecord> pageRecord(Long busId,Pageable pageable){
        return busRecordRepository.findAll(this.recordParam(busId),PageUtil.getPageable(pageable));
    }

    private Specification<BusRecord> recordParam(Long busId){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(busId!=null && busId>0)
                list.add(cb.equal(root.get("busId"),busId));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
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


    public Map<String, BusStats> statistics(Long userId){
        Date date=new Date();
        String day=repository.statistics(DateUtil.SQL_DAY,DateUtil.toDay(date));
        String week=repository.statistics(DateUtil.SQL_WEEK,DateUtil.toWeek(date));
        String month=repository.statistics(DateUtil.SQL_MONTH,DateUtil.toMonth(date));
        String year=repository.statistics(DateUtil.SQL_YEAR,DateUtil.toYear(date));

        Map<String,BusStats> result=new HashMap<>();

        result.put("day",this.getBusStats(day));
        result.put("week",this.getBusStats(week));
        result.put("month",this.getBusStats(month));
        result.put("year",this.getBusStats(year));

        return result;
    }

    public Map<String, BusStats> statisticsOfGroup(Long orgId){
        Map<String,BusStats> result=new HashMap<>();

        return result;
    }


    private BusStats getBusStats(String data){
        String[] str=data.split(",");
        int a=Integer.valueOf(str[0]);
        int b=Integer.valueOf(str[1]);
        int c=Integer.valueOf(str[2]);
        return new BusStats(a,b,c);
    }
}
