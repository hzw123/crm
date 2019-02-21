package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.repository.BusinessOpportunityRepository;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.PageUtil;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crm/v1/bus")
public class BusinessOpportunityController extends BaseController{

    @Autowired
    private BusinessOpportunityRepository repository;

    @GetMapping("{id}")
    @ApiOperation("根据id查询商机")
    public Result findById(@PathVariable Long id){

        BusinessOpportunity businessOpportunity=repository.findById(id).get();

        if(businessOpportunity==null)
            return error("没有找到id:"+id+"的商机");

        return ok(businessOpportunity);
    }

    @GetMapping
    @ApiOperation("查询所有商机")
    public Result findAll(){

        List<BusinessOpportunity> list=repository.findAll();

        if(list==null || list.size()==0)
            return error("还没有的商机");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询商机")
    public Result page(Pageable pageable){

        return ok(repository.findAll((root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            return cb.and(list.toArray(new Predicate[list.size()]));
        },PageUtil.getPageable(pageable)));
    }

    @PutMapping("/{id}")
    @ApiOperation("根据id修改合同状态")
    public Result updateStatus(@PathVariable Long id,Integer status) {

        repository.updateStatus(id,status);

        return ok("修改成功");
    }


    @DeleteMapping("/{id}")
    @ApiOperation("根据id修改合同状态")
    public Result deleteById(@PathVariable Long id) {

        repository.deleteById(id);

        return ok("删除id:"+id+"成功");
    }

}
