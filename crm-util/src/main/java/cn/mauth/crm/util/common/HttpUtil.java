package cn.mauth.crm.util.common;

import com.alibaba.fastjson.JSON;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public final class HttpUtil {

    public static ServletRequestAttributes getServletRequestAttributes(){
        return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest(){
        return HttpUtil.getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return HttpUtil.getServletRequestAttributes().getResponse();
    }

    public static HttpSession getSession(){
        return HttpUtil.getRequest().getSession();
    }

    public static void addSession(String key,Object value){
        HttpUtil.getSession().setAttribute(key,value);
    }

    public static void removeSession(String key){
        HttpUtil.getSession().removeAttribute(key);
    }

    public Cookie[] getCookie(){
        return HttpUtil.getRequest().getCookies();
    }

    public static void addCookie(String key,String value){

        Assert.notNull(key,"cookie的值不能为空");

        HttpUtil.getResponse().addCookie(new Cookie(key,value));
    }

    public static void sendCall(ServletResponse res, int httpStatus, String message){
        HttpServletResponse response=(HttpServletResponse) res;

        response.setStatus(httpStatus);

        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json; charset=utf-8");

        PrintWriter out = null;

        try {

            out = response.getWriter();

            String data = JSON.toJSONString(Result.of(httpStatus,message));

            out.append(data);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            out.write(e.getMessage());
        } finally {

            if (out != null) {
                out.close();
            }
        }
    }

}
