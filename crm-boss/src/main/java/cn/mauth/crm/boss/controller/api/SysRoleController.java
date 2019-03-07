package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.bean.Role;
import cn.mauth.crm.common.service.SysRoleService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/crm/v1/roles")
@ApiModel("角色API")
public class SysRoleController extends BaseController{

    @Autowired
    private SysRoleService service;

    @GetMapping("/list")
    @ApiOperation("获得全部角色列表")
    public Result list(Role role){
        return ok(service.findAll(role));
    }

    @GetMapping("/page")
    @ApiOperation("通过分页获得角色列表")
    public Result page(Role role,Pageable pageable){
        return ok(service.page(role,pageable));
    }

    @GetMapping("/{id}")
    @ApiOperation("通过id获得角色信息")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @PostMapping
    @ApiOperation("添加角色信息")
    public Result add(Role role){
        if(service.add(role)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("通过id修给角色信息")
    public Result update(@PathVariable Long id, Role role){
        if(service.update(role)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("通过id删除角色信息")
    public Result deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
