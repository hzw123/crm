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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
