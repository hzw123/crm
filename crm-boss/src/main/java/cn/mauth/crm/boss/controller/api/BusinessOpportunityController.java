package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.BusinessOpportunity;
import cn.mauth.crm.common.domain.Stage;
import cn.mauth.crm.common.service.BusiService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import cn.mauth.crm.util.enums.TimeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/bus")
@ApiModel("商机管理")
public class BusinessOpportunityController extends BaseController{

    @Autowired
    private BusiService service;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询商机")
    public Result findById(@PathVariable Long id){

        BusinessOpportunity businessOpportunity=service.findById(id);

        if(businessOpportunity==null)
            return error("没有找到id:"+id+"的商机");

        return ok(businessOpportunity);
    }

    @GetMapping
    @ApiOperation("查询所有商机")
    public Result findAll(Long accountId,int status,String state){
        return ok(service.findAll(accountId,status,state));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询商机")
    public Result page(Long accountId,int status,String state,Pageable pageable){
        return ok(service.page(accountId,status,state,pageable));
    }

    @PostMapping
    @ApiOperation("添加一个商机")
    public Result add(BusinessOpportunity businessOpportunity) {
        if(service.add(businessOpportunity)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}/status")
    @ApiOperation("根据id修改商机状态")
    public Result updateStatus(@PathVariable Long id,Integer status) {

        if(service.updateStatus(id,status)){
            return ok("修改成功");
        }

        return error("修改失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id修改商机")
    public Result update(@PathVariable Long id,BusinessOpportunity businessOpportunity) {

        if(service.update(businessOpportunity)){
            return ok("修改成功");
        }

        return error("修改失败");
    }


    @PostMapping("/{id}/delete")
    @ApiOperation("根据id删除商机")
    public Result deleteById(@PathVariable Long id) {

        service.deleteById(id);

        return ok("删除id:"+id+"成功");
    }

    @GetMapping("/{id}/record")
    @ApiOperation("查询商机Id的所有记录")
    public Result record(@PathVariable Long id) {

        service.findRecord(id);

        return ok("删除id:"+id+"成功");
    }

    @GetMapping("/{id}/record/page")
    @ApiOperation("根据商机Id分页查询记录")
    public Result recordPage(@PathVariable Long id,Pageable pageable) {

        service.pageRecord(id,pageable);

        return ok("删除id:"+id+"成功");
    }

    @GetMapping("/statistics/{orgId}")
    @ApiOperation("统计商机的数据")
    public Result statistics(@PathVariable Long orgId,boolean isUser) {
        return ok(service.statistics(orgId,isUser));
    }

    @GetMapping("/statistics/{orgId}/time")
    @ApiOperation("根据条件统计商机的数据")
    public Result statisticsFromTime(@PathVariable Long orgId, boolean isUser, TimeEnum format, String time) {

        return ok(service.getBusStats(format.getName(),time,orgId,isUser));
    }

}
