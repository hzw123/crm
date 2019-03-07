package cn.mauth.crm.common.service;

import cn.mauth.crm.common.repository.SysUserInfoRepository;
import cn.mauth.crm.util.base.BaseEntity;
import cn.mauth.crm.util.base.BaseRepository;
import cn.mauth.crm.util.common.NumUtil;
import cn.mauth.crm.util.common.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public abstract class BaseService<D extends BaseRepository,T extends BaseEntity>{

    protected final static Logger log= LoggerFactory.getLogger(BaseService.class);

    protected final D repository;

    protected SysUserInfoRepository sysUserInfoRepository;

    public BaseService(D repository, SysUserInfoRepository sysUserInfoRepository) {
        this.repository = repository;
        this.sysUserInfoRepository = sysUserInfoRepository;
    }

    protected Pageable getPageAble(Pageable pageable){
        return PageUtil.getPageable(pageable);
    }

    protected Pageable getPageAble(Pageable pageable,Sort sort){
        return PageUtil.getPageable(pageable,sort);
    }

    public Page<T> page(Pageable pageable){
        return repository.findAll(this.getPageAble(pageable));
    }

    public T findById(Long id){
        return (T)repository.findById(id).get();
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public boolean add(T t){
        boolean flag=false;
        try {
            if(NumUtil.toLong(t.getCreatorId()))
                t.setCreatorName(sysUserInfoRepository.findNameById(Long.valueOf(t.getCreatorId())));

            if(NumUtil.toLong(t.getModifiedId()))
                t.setModifiedName(sysUserInfoRepository.findNameById(Long.valueOf(t.getModifiedId())));

            if(NumUtil.toLong(t.getOwnerId()))
                t.setOwnerName(sysUserInfoRepository.findNameById(Long.valueOf(t.getOwnerId())));

            repository.save(t);

            flag=true;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return flag;
    }

    public boolean update(T t){
        boolean flag=false;
        try {

            T old=this.findById(t.getId());

            if(old!=null){

                t.setCreateAt(old.getCreateAt());

                repository.save(t);

                flag=true;
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
        return flag;
    }


    public boolean deleteById(Long id){
        boolean flag=false;
        try {
            repository.deleteById(id);

            flag=true;
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return flag;
    }


}
