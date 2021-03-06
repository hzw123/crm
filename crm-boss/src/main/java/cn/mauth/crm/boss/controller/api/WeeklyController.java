package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Visit;
import cn.mauth.crm.common.domain.Weekly;
import cn.mauth.crm.common.service.WeeklySerVice;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/weeklys")
@ApiModel("周报API")
public class WeeklyController extends BaseController{

    @Autowired
    private WeeklySerVice service;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询周报")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation("查询所有周报")
    public Result findAll(Weekly weekly){
        return ok(service.findAll());
    }

    @GetMapping("/page")
    @ApiOperation("分页查询周报")
    public Result page(Weekly weekly,Pageable pageable){
        return ok(service.page(pageable));
    }


    @PostMapping
    @ApiOperation("添加一个周报")
    public Result add(Weekly weekly) {
        if(service.add(weekly)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id修改周报")
    public Result update(@PathVariable Long id,Weekly weekly) {
        if(service.update(weekly)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("根据id删除周报")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
