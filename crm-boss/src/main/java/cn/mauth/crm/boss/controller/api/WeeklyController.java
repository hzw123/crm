package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Weekly;
import cn.mauth.crm.common.service.WeeklySerVice;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/weeklys")
@ApiModel("周报API")
public class WeeklyController extends BaseController{

    @Autowired
    private WeeklySerVice service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询周报")
    public Result findById(@PathVariable Long id){

        Weekly weekly=service.findById(id);

        if(weekly==null)
            return error("没有找到id:"+id+"的周报");

        return ok(weekly);
    }

    @GetMapping
    @ApiOperation("查询所有周报")
    public Result findAll(){

        List<Weekly> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有周报");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询周报")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }
}
