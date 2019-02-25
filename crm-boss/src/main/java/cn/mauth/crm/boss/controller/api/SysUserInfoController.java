package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.SysUserInfo;
import cn.mauth.crm.common.service.SysUserInfoService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/users")
@ApiModel("用户API")
public class SysUserInfoController extends BaseController{

    @Autowired
    private SysUserInfoService service;

    @GetMapping("/{id}")
    @ApiOperation("通过id获得用户")
    public Result findById(@PathVariable Long id){
        SysUserInfo user=service.findById(id);
        if(user==null){
            return error("没有找到id为"+id+"的用户");
        }
        if(user.isDisabled()){
            return error("用户id:"+id+"已经删除");
        }
        return ok(user);
    }

    @GetMapping
    @ApiOperation("根据角色获得用户列表")
    public Result getAdministratorList(@RequestParam(value = "type") String type){

        List<SysUserInfo> list=service.findAdminList(type);

        if(list==null||list.size()==0){
            return ok("没有找到一个用户");
        }

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页获得用户信息")
    public Result getAdministratorList(Pageable pageable){
        return ok(service.page(pageable));
    }

    @PutMapping("/{id}")
    @ApiOperation("修改用户信息")
    public Result update(@PathVariable Long id,SysUserInfo sysUserInfo){
        if(service.update(sysUserInfo)){
            return ok("删除成功");
        }
        return error("删除失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户信息")
    public Result delete(@PathVariable Long id){
        if(service.noDisabled(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
