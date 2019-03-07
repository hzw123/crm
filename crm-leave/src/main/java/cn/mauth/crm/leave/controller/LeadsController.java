package cn.mauth.crm.leave.controller;

import cn.mauth.crm.common.domain.Leads;
import cn.mauth.crm.common.repository.LeadsRepository;
import cn.mauth.crm.util.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crm/leads")
public class LeadsController {

    private final Logger log= LoggerFactory.getLogger(LeadsController.class);

    @Autowired
    private LeadsRepository repository;

    @GetMapping("/{code}")
    public String leads(@PathVariable String code, ModelMap model){

        log.info("进入调查问卷");

        model.put("code",code);
        return "leads";
    }

    @ResponseBody
    @PostMapping("/{code}")
    public Result save(@PathVariable String code, Leads leads){
        return Result.SUCCESS;
    }
}
