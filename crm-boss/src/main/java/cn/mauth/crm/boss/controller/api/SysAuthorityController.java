package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.SysAuthority;
import cn.mauth.crm.common.service.SysAuthService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/auths")
@ApiModel("权限API")
public class SysAuthorityController extends BaseController{

    @Autowired
    private SysAuthService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询权限")
    public Result findById(@PathVariable Long id){

        SysAuthority auth=service.findById(id);

        if(auth==null)
            return error("没有找到id:"+id+"的权限");

        return ok(auth);
    }

    @GetMapping
    @ApiOperation("查询所有权限")
    public Result findAll(){

        List<SysAuthority> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有权限");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询权限")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }

    @PostMapping
    @ApiOperation("添加一个权限")
    public Result error(SysAuthority sysAuthority) {
        if(service.add(sysAuthority)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("修改权限")
    public Result update(@PathVariable Long id,SysAuthority sysAuthority) {
        if(service.update(sysAuthority)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除权限")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
