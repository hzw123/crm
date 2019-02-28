package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.SysRole;
import cn.mauth.crm.common.service.SysRoleService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/roles")
@ApiModel("角色API")
public class SysRoleController extends BaseController{

    @Autowired
    private SysRoleService service;

    @GetMapping("/list")
    @ApiOperation("获得全部角色列表")
    public Result list(){
        List<SysRole> list=service.findAll();
        if(list==null||list.size()==0)
            return ok("一个也没有找到");
        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("通过分页获得角色列表")
    public Result page(Pageable pageable){

        Page<SysRole> page=service.page(pageable);

        return ok(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("通过id获得角色信息")
    public Result findById(@PathVariable Long id){
        SysRole role=service.findById(id);
        if(role==null)
            return ok("没有找到id为"+id+"的角色");
        return ok(role);
    }

    @PostMapping
    @ApiOperation("添加角色信息")
    public Result add(SysRole sysRole){
        if(service.add(sysRole)){
            return ok("添加成功");
        }
        return ok("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("通过id修给角色信息")
    public Result update(@PathVariable Long id, SysRole sysRole){
        if(service.update(sysRole)){
            return ok("修改成功");
        }
        return ok("修改失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过id删除角色信息")
    public Result deleteById(@PathVariable Long id){
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return ok("删除失败");
    }
}
