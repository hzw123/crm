package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.domain.Organization;
import cn.mauth.crm.common.domain.OrganizeEmployee;
import cn.mauth.crm.common.service.OrganizationService;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import cn.mauth.crm.util.common.SessionUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm/v1/admin")
public class AdminController extends BaseController{

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/join")
    @ApiOperation("通过机构code加入")
    public Result join(String code){

        Organization org=organizationService.findByCode(code);

        if(null==org){
            return error("没有该机构");
        }

        organizationService.addUser(new OrganizeEmployee(org.getId(), SessionUtil.getUserId()));

        return ok("你已加入"+org.getName());
    }
}
