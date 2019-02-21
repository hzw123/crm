package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Visit;
import cn.mauth.crm.common.service.VisitService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/visits")
@Api("拜访API")
public class VisitController extends BaseController{

    @Autowired
    private VisitService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询拜访")
    public Result findById(@PathVariable Long id){

        Visit visit=service.findById(id);

        if(visit==null)
            return error("没有找到id:"+id+"的拜访");

        return ok(visit);
    }

    @GetMapping
    @ApiOperation("查询所有拜访")
    public Result findAll(){

        List<Visit> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有拜访");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询拜访")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }
}
