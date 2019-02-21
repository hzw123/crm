package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.repository.RecordRepository;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.PageUtil;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crm/v1/records")
public class RecordController extends BaseController{

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result page(Pageable pageable){
        return ok(recordRepository.findAll(((root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            return cb.and(list.toArray(new Predicate[list.size()]));
        }),PageUtil.getPageable(pageable)));
    }
}
