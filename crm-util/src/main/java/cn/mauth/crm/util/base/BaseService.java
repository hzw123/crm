package cn.mauth.crm.util.base;

import cn.mauth.crm.util.common.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public abstract class BaseService<D extends BaseRepository,T extends BaseEntity>{

    protected final D repository;

    protected final static Logger log= LoggerFactory.getLogger(BaseService.class);


    public BaseService(D repository) {
        this.repository = repository;
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
