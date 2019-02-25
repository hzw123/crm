package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Account;
import cn.mauth.crm.common.service.AccountService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/accounts")
@ApiModel("客户API")
public class AccountController extends BaseController{

    @Autowired
    private AccountService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询客户")
    public Result findById(@PathVariable Long id){

        Account account=service.findById(id);

        if(account==null)
            return error("没有找到id:"+id+"的客户");

        return ok(account);
    }

    @GetMapping
    @ApiOperation("查询所有客户")
    public Result findAll(){

        List<Account> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有客户");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询客户")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }

    @PostMapping
    @ApiOperation("添加一个客户")
    public Result error(Account account) {
        if(service.add(account)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("修改客户")
    public Result update(@PathVariable Long id,Account account) {
        if(service.update(account)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除客户")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
