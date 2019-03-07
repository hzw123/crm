package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.bean.UserBean;
import cn.mauth.crm.common.domain.Organization;
import cn.mauth.crm.common.domain.OrganizeEmployee;
import cn.mauth.crm.common.service.OrganizationService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm/v1/org")
@ApiModel("机构API")
public class OrganizationController extends BaseController{
    
    @Autowired
    private OrganizationService service;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询机构")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation("根据条件查询所有机构")
    public Result findAll(Organization organization){
        return ok(service.findAll(organization));
    }

    @GetMapping("/page")
    @ApiOperation("根据条件分页查询机构")
    public Result page(Organization organization,Pageable pageable){
        return ok(service.page(organization,pageable));
    }

    @PostMapping
    @ApiOperation("添加一个机构")
    public Result add(Organization organization) {
        if(service.add(organization)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id修改机构")
    public Result update(@PathVariable Long id,Organization organization) {
        if(service.update(organization)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("根据id删除机构")
    public Result deleteBy(@PathVariable Long id) {
        return service.removeById(id);
    }

    @GetMapping("/{id}/users")
    @ApiOperation("查询机构下的用户")
    public Result findUsersByOrgId(@PathVariable Long id){
        return ok(service.findUserByOrg(id));
    }

    @PostMapping("/{id}/users")
    @ApiOperation("往机构里添加用户")
    public Result addUsers(@PathVariable Long id, UserBean userBean){

        if(userBean.getId()<=0){
            if(userBean.getIds()==null||userBean.getIds().size()==0){
                return error("没有添加的用户");
            }

            if(!service.addUsers(id,userBean.getIds())){
                return error("添加失败");
            }
        }else{
            service.addUser(new OrganizeEmployee(id,userBean.getId()));
        }

        return ok("添加成功");
    }

    @PostMapping("/{id}/users/manager")
    @ApiOperation("在机构里创建主管")
    public Result addUsers(@PathVariable Long id, OrganizeEmployee  organizeEmployee){

        organizeEmployee.setId(0L);

        organizeEmployee.setOrgId(id);

        organizeEmployee.setManager(true);

        service.addUser(organizeEmployee);

        return ok("创建主管成功");
    }

    @PostMapping("/{id}/users/delete")
    @ApiOperation("从机构里移除用户")
    public Result removeUsers(@PathVariable Long id, UserBean userBean){
        if(userBean.getId()<=0){
            if(userBean.getIds()==null||userBean.getIds().size()==0){
                return error("没有移除的用户");
            }
            service.removeUser(id,userBean.getIds());
        }else{
            service.removeUser(id,userBean.getId());
        }

        return ok("移除成功");
    }

    @PostMapping("/{id}/users/put")
    @ApiOperation("修改机构里用户的信息")
    public Result updateUser(@PathVariable Long id, OrganizeEmployee organizeEmployee){

        if(id!=organizeEmployee.getOrgId()){
            return error("机构Id错误");
        }
        service.updateOrgUser(organizeEmployee);

        return ok("修改成功");
    }
}

