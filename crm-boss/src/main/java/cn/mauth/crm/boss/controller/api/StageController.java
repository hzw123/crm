package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Stage;
import cn.mauth.crm.common.service.StageService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crm/v1/stages")
@ApiModel("阶段api")
public class StageController extends BaseController{

    @Autowired
    private StageService service;

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id){
        return ok(service.findById(id));
    }

    @GetMapping
    public Result findAll(Stage stage){
        return ok(service.findAll(stage));
    }

    @GetMapping("/page")
    @ApiOperation("根据条件分页显示")
    public Result page(Stage stage, Pageable pageable){
        return ok(service.page(stage,pageable));
    }

    @PostMapping
    @ApiOperation("添加一个阶段")
    public Result add(Stage stage){
        if(service.add(stage)){
            return ok("创建成功");
        }
        return error("创建失败");
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id修改阶段")
    public Result update(@PathVariable Long id,Stage stage) {
        if(service.update(stage)){
            return ok("修改成功");
        }
        return error("修改失败");
    }

    @PostMapping("/{id}/delete")
    @ApiOperation("根据id删除阶段")
    public Result deleteBy(@PathVariable Long id) {
        if(service.deleteById(id)){
            return ok("删除成功");
        }
        return error("删除失败");
    }
}
