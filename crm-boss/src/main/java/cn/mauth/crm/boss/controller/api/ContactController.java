package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Contact;
import cn.mauth.crm.common.service.ContactService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm/v1/contacts")
@ApiModel("联系人信息API")
public class ContactController extends BaseController{
    
    @Autowired
    private ContactService service;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询联系人信息")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation("查询所有联系人信息")
    public Result findAll(Contact contact){
        return ok(service.findAll(contact));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询联系人信息")
    public Result page(Contact contact,Pageable pageable){

        return ok(service.page(contact,pageable));
    }

    @PostMapping
    @ApiOperation("添加一个联系人信息")
    public Result add(Contact contact) {
        if(service.add(contact)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id修改联系人信息")
    public Result update(@PathVariable Long id,Contact contact) {
        if(service.add(contact)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("根据id删除联系人信息")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
