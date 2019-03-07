package cn.mauth.crm.common.service;

import cn.mauth.crm.common.domain.Stage;
import cn.mauth.crm.common.repository.StageRepository;
import cn.mauth.crm.util.common.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository repository;

    public Stage findById(Long id){
        return repository.findById(id).get();
    }

    public List<Stage> findAll(Stage stage){
        return repository.findAll(this.stageSpecification(stage), Sort.by(Sort.Direction.ASC,"sort"));
    }

    public Page<Stage> page(Stage stage,Pageable pageable){

        return repository.findAll(this.stageSpecification(stage), PageUtil.getPageable(pageable,Sort.by(Sort.Direction.ASC,"sort")));
    }

    private Specification<Stage> stageSpecification(Stage stage){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(stage.getOrgId()!=null)
                list.add(cb.equal(root.get("orgId"),stage.getOrgId()));
            if(stage.getType()!=null)
                list.add(cb.equal(root.get("type"),stage.getType()));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }

    public boolean add(Stage stage){

        boolean flag=false;
        try {
            repository.save(stage);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean update(Stage stage){
        boolean flag=false;
        try {
            Stage old=repository.findById(stage.getId()).get();
            stage.setCreateAt(old.getCreateAt());
            repository.save(stage);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteById(Long id){
        boolean flag=false;
        try {
            repository.deleteById(id);
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
