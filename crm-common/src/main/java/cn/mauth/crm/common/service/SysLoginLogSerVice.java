package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.SysLoginLog;
import cn.mauth.crm.common.repository.SysLoginLogRepository;
import cn.mauth.crm.util.common.HttpUtil;
import cn.mauth.crm.util.common.PageUtil;
import cn.mauth.crm.util.common.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysLoginLogSerVice {

    private final static Logger log= LoggerFactory.getLogger(SysLoginLog.class);

    @Autowired
    private SysLoginLogRepository repository;


    public boolean add(SysLoginLog sysLoginLog){
        boolean flag=false;
        try {
            repository.save(sysLoginLog);
            flag=true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return flag;
    }

    public SysLoginLog findById(Long id){
        return repository.findById(id).get();
    }

    public Page<SysLoginLog> page(Pageable pageable){

        return repository.findAll((root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(!SessionUtil.isAdmin()){

                list.add(cb.equal(root.get("userId"),SessionUtil.getUserId()));
            }

            return cb.and(list.toArray(new Predicate[list.size()]));
        }, PageUtil.getPageable(pageable));
    }

    public List<SysLoginLog> findAll(){
        return repository.findAll((root, query, cb) -> {
            if(!SessionUtil.isAdmin()){
                return cb.equal(root.get("userId"),SessionUtil.getUserId());
            }
            return null;
        });
    }
}
