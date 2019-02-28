package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.common.domain.Target;
import cn.mauth.crm.common.service.TargetService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/targets")
@ApiModel("目标API")
public class TargetController extends BaseController{
    
    @Autowired
    private TargetService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询目标")
    public Result findById(@PathVariable Long id){

        Target target=service.findById(id);

        if(target==null)
            return error("没有找到id:"+id+"的目标");

        return ok(target);
    }

    @GetMapping
    @ApiOperation("查询所有目标")
    public Result findAll(){

        List<Target> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有目标");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询目标")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }

    @PostMapping
    @ApiOperation("添加一个目标")
    public Result add(Target target) {
        if(service.add(target)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("根据id修改目标")
    public Result update(@PathVariable Long id,Target target) {
        if(service.update(target)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除目标")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
