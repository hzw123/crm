package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.repository.SysLoginLogRepository;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm/v1/log")
public class SysLoginLogController extends BaseController{

    @Autowired
    private SysLoginLogRepository repository;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询登录日志")
    public Result findById(@PathVariable Long id) {

        return ok(repository.findById(id));
    }

    @GetMapping
    @ApiOperation("查询所有登录日志")
    public Result findAll() {
        return ok(repository.findAll());
    }

    @GetMapping("/page")
    @ApiOperation("分页查询登录日志")
    public Result page(Pageable pageable) {
       return ok();
    }
}
