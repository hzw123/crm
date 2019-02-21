package cn.mauth.crm.util.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.util.Assert;

public final class SessionUtil {

    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    public static Session getSession(boolean create){
        return SecurityUtils.getSubject().getSession(create);
    }

    public static void addSession(String key,Object value){

        Assert.notNull(key,"key不能为空");

        SessionUtil.getSession().setAttribute(key,value);
    }

    public static Object getValue(String key){

        Assert.notNull(key,"key不能为空");

        return SessionUtil.getSession().getAttribute(key);
    }

    public static Object removeSession(String key){

        Assert.notNull(key,"key不能为空");

        return SessionUtil.getSession().removeAttribute(key);
    }

    public static void saveUser(Object value){
        SessionUtil.addSession(Constants.Session.USER,value);
    }

    public static Object getUser(){
        return SessionUtil.getValue(Constants.Session.USER);
    }

    public static void saveAuth(Object value){
        SessionUtil.addSession(Constants.Session.PERMISSIONS,value);
    }

    public static Object getAuth(){
        return SessionUtil.getValue(Constants.Session.PERMISSIONS);
    }

    public static void saveUserId(Object value){
        SessionUtil.addSession(Constants.Session.USER_ID,value);
    }

    public static Long getUserId(){
        return (Long) SessionUtil.getValue(Constants.Session.USER_ID);
    }

    public static void saveRoles(Object value){
        SessionUtil.addSession(Constants.Session.ROLE,value);
    }

    public static Object getRoles(){
        return SessionUtil.getValue(Constants.Session.ROLE);
    }
}
