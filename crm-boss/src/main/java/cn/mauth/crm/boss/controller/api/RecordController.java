package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Record;
import cn.mauth.crm.common.repository.RecordRepository;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.PageUtil;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crm/v1/records")
@ApiModel("记录API")
public class RecordController extends BaseController{

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result page(String type,String userName,Pageable pageable){
        return ok(recordRepository.findAll(this.specification(type,userName),PageUtil.getPageable(pageable)));
    }

    @GetMapping
    @ApiOperation("查询全部")
    public Result list(String type,String userName){
        return ok(recordRepository.findAll(this.specification(type,userName)));
    }

    private Specification<Record> specification(String type,String userName){
        return (root, query, cb) -> {
            List<Predicate> list=new ArrayList<>();

            if(StringUtils.isNotEmpty(type))
                list.add(cb.equal(root.get("type"),type));

            if(StringUtils.isNotEmpty(userName))
                list.add(cb.equal(root.get("userName"),userName));

            return cb.and(list.toArray(new Predicate[list.size()]));
        };
    }
}
