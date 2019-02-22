package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.common.domain.Organization;
import cn.mauth.crm.common.service.OrganizationService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/org")
@ApiModel("机构API")
public class OrganizationController extends BaseController{
    
    @Autowired
    private OrganizationService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询机构")
    public Result findById(@PathVariable Long id){

        Organization org=service.findById(id);

        if(org==null)
            return error("没有找到id:"+id+"的机构");

        return ok(org);
    }

    @GetMapping
    @ApiOperation("查询所有机构")
    public Result findAll(){

        List<Organization> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有机构");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询机构")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }

    @PostMapping
    @ApiOperation("添加一个机构")
    public Result error(Organization organization) {
        if(service.add(organization)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("修改机构")
    public Result update(@PathVariable Long id,Organization organization) {
        if(service.add(organization)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除机构")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}

