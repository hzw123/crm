package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Visit;
import cn.mauth.crm.common.service.VisitService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm/v1/visits")
@ApiModel("拜访API")
public class VisitController extends BaseController{

    @Autowired
    private VisitService service;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询拜访")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation("查询所有拜访")
    public Result findAll(Visit visit){
        return ok(service.findAll());
    }

    @GetMapping("/page")
    @ApiOperation("分页查询拜访")
    public Result page(Visit visit,Pageable pageable){
        return ok(service.page(pageable));
    }

    @PostMapping
    @ApiOperation("添加一个拜访")
    public Result add(Visit visit) {
        if(service.add(visit)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id修改拜访")
    public Result update(@PathVariable Long id,Visit visit) {
        if(service.update(visit)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("根据id删除拜访")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
