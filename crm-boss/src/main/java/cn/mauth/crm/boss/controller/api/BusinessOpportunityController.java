package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.service.BusiService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/bus")
@ApiModel("商机API")
public class BusinessOpportunityController extends BaseController{

    @Autowired
    private BusiService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询商机")
    public Result findById(@PathVariable Long id){

        BusinessOpportunity businessOpportunity=service.findById(id);

        if(businessOpportunity==null)
            return error("没有找到id:"+id+"的商机");

        return ok(businessOpportunity);
    }

    @GetMapping
    @ApiOperation("查询所有商机")
    public Result findAll(){

        List<BusinessOpportunity> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有的商机");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询商机")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }

    @PostMapping
    @ApiOperation("添加一个商机")
    public Result error(BusinessOpportunity businessOpportunity) {
        if(service.add(businessOpportunity)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("根据id修改合同状态")
    public Result updateStatus(@PathVariable Long id,Integer status) {

        if(service.updateStatus(id,status)){
            return ok("修改成功");
        }

        return error("修改失败");
    }


    @DeleteMapping("/{id}")
    @ApiOperation("根据id修改合同状态")
    public Result deleteById(@PathVariable Long id) {

        service.deleteById(id);

        return ok("删除id:"+id+"成功");
    }

}
