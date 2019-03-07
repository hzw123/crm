package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.util.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping
    public Result message(){
        return Result.success();
    }
}
