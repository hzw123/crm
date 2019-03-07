package cn.mauth.crm.common.service;

import cn.mauth.crm.common.bean.BusStats;
import cn.mauth.crm.common.bean.StageStats;
import cn.mauth.crm.common.domain.BusRecord;
import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.repository.BusRecordRepository;
import cn.mauth.crm.common.repository.BusinessOpportunityRepository;
import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.util.common.DateUtil;
import cn.mauth.crm.util.common.PageUtil;
import cn.mauth.crm.util.common.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.*;

@Service
public class BusiService extends BaseService<BusinessOpportunityRepository,BusinessOpportunity>{

    @Autowired
    private BusRecordRepository busRecordRepository;

    public BusiService(BusinessOpportunityRepository repository, SysUserInfoRepository sysUserInfoRepository) {
        super(repository, sysUserInfoRepository);
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


    public Map<String, BusStats> statistics(Long orgId,boolean isUser){
        Date date=new Date();

        Map<String,BusStats> result=new HashMap<>();

        result.put("day",this.getBusStats(DateUtil.SQL_DAY,DateUtil.toDay(date),orgId,isUser));
        result.put("week",this.getBusStats(DateUtil.SQL_WEEK,DateUtil.toWeek(date),orgId,isUser));
        result.put("month",this.getBusStats(DateUtil.SQL_MONTH,DateUtil.toMonth(date),orgId,isUser));
        result.put("year",this.getBusStats(DateUtil.SQL_YEAR,DateUtil.toYear(date),orgId,isUser));

        return result;
    }


    private BusStats getBusStats(String data){
        if(StringUtils.isEmpty(data))
            return null;
        String[] str=data.split(",");
        if(str.length!=5)
            return null;
        int a=Integer.valueOf(str[0]);
        double a1=str[1].equals("null")?0:Double.valueOf(str[1]);
        int b=str[2].equals("null")?0:Integer.valueOf(str[2]);
        double b1=str[3].equals("null")?0:Double.valueOf(str[3]);
        int c=str[4].equals("null")?0:Integer.valueOf(str[4]);
        return new BusStats(a,a1,b,b1,c);
    }

    private List<StageStats> getStageStats(List<Object[]> data){
        if(data==null||data.size()==0)
            return null;
        List<StageStats> list=new ArrayList<>();

        Object[] obj=null;

        for (int i=0;i<data.size();i++){
            obj=data.get(i);
            String name=(String) obj[0];
            int sort=(int) obj[1];
            int total=((BigInteger) obj[2]).intValue();
            double totalAmount=(double) obj[3];
            list.add(new StageStats(name,sort,total,totalAmount));
        }

        return list;
    }

    public BusStats getBusStats(String format,String timeStr,Long orgId,boolean isUser){
        String data=null;
        List<Object[]> stageDada=null;
        if(isUser){

            data=repository.statisticsFromOrg(format,timeStr,orgId);

            stageDada=repository.statisticsStage(format, timeStr, orgId);
        }else{
            String ownerId=String.valueOf(SessionUtil.getUserId());

            data=repository.statisticsFromOwnerId(format,timeStr,ownerId);

            stageDada=repository.statisticsStage(format, timeStr, orgId,ownerId);
        }


        BusStats busStats=this.getBusStats(data);

        busStats.setList(this.getStageStats(stageDada));

        return busStats;
    }


}
