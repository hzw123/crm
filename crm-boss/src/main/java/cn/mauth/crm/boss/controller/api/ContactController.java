package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Client;
import cn.mauth.crm.common.domain.Contact;
import cn.mauth.crm.common.service.ContactService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/contacts")
@Api("联系人API")
public class ContactController extends BaseController{
    
    @Autowired
    private ContactService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询联系人")
    public Result findById(@PathVariable Long id){

        Contact contact=service.findById(id);

        if(contact==null)
            return error("没有找到id:"+id+"的联系人");

        return ok(contact);
    }

    @GetMapping
    @ApiOperation("查询所有联系人")
    public Result findAll(){

        List<Contact> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有联系人");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询联系人")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }
}
