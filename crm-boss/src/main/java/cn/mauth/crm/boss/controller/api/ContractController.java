package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Contract;
import cn.mauth.crm.common.service.ContractService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/v1/contracts")
@ApiModel("合同API")
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
    public Result findAll(String name,String code,String type, String title,
                          Long accountId,Long busId){

        List<Contract> list=service.findAll(name, code, type, title, accountId, busId);

        if(list==null || list.size()==0)
            return error("还没有合同");

        return ok(list);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询合同")
    public Result page(String name,String code,String type, String title,
                       Long accountId,Long busId,Pageable pageable){

        return ok(service.page(name,code,type,title,accountId,busId,pageable));
    }


    @PostMapping
    @ApiOperation("添加一个合同")
    public Result add(Contract contract) {
        if(service.add(contract)){
            return ok("添加成功");
        }
        return error("添加失败");
    }

    @PutMapping("/{id}")
    @ApiOperation("根据id修改合同")
    public Result update(@PathVariable Long id,Contract contract) {
        if(service.update(contract)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除合同")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
