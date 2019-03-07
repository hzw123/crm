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

@RestController
@RequestMapping("/crm/v1/accounts")
@ApiModel("客户API")
public class AccountController extends BaseController{

    @Autowired
    private AccountService service;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询客户")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @GetMapping
    @ApiOperation("查询所有客户")
    public Result findAll(Account account){
        return ok(service.findAll(account));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询客户")
    public Result page(Account account,Pageable pageable){
        return ok(service.page(account,pageable));
    }

    @PostMapping
    @ApiOperation("添加一个客户")
    public Result add(Account account) {
        if(service.add(account)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("修改客户")
    public Result update(@PathVariable Long id,Account account) {
        if(service.update(account)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("删除客户")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }

    @GetMapping("/{id}/stats")
    @ApiOperation("客户统计")
    public Result accountStats(@PathVariable Long id){
        return ok(service.accountStats(id));
    }
}
