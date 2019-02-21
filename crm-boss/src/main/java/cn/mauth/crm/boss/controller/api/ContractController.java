package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.common.service.ContractService;
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
@RequestMapping("/crm/v1/contracts")
@Api("合同API")
public class ContractController extends BaseController{
    
    @Autowired
    private ContractService service;

    @GetMapping("{id}")
    @ApiOperation("根据id查询合同")
    public Result findById(@PathVariable Long id){

        Contract contract=service.findById(id);

        if(contract==null)
            return error("没有找到id:"+id+"的合同");

        return ok(contract);
    }

    @GetMapping
    @ApiOperation("查询所有合同")
    public Result findAll(){

        List<Contract> list=service.findAll();

        if(list==null || list.size()==0)
            return error("还没有合同");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询合同")
    public Result page(Pageable pageable){

        return ok(service.page(pageable));
    }
}
