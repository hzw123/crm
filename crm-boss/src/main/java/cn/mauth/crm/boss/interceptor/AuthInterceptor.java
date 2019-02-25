package cn.mauth.crm.boss.interceptor;

import cn.mauth.crm.common.bean.SessionInfo;
import cn.mauth.crm.common.service.RedisService;
import cn.mauth.crm.util.common.Constants;
import cn.mauth.crm.util.common.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String sessionId=request.getParameter(Constants.SESSIN_ID);

        if(StringUtils.isEmpty(sessionId)){
            HttpUtil.sendCall(response, 401,"sessionId不能为空");
            return false;
        }

        SessionInfo sessionInfo=redisService.getSessionInfo(sessionId);

        if(null==sessionInfo){
            HttpUtil.sendCall(response, 401,"没有权限");
            return false;
        }

        return true;
    }
}
