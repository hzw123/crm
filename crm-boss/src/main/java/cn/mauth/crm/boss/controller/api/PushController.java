package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.common.service.WebSocketServer;
import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/crm/v1/socket")
public class PushController extends BaseController{


    @RequestMapping("/push/{cid}")
    public Result push(@PathVariable String cid,String message){
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            log.error(e.getMessage());
            return error(e.getMessage());
        }
        return ok("push cid:"+cid+"成功");
    }
}
