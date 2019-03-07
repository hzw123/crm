package cn.mauth.crm.boss.controller;

import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *异常处理
 */
@RestControllerAdvice
public class BossExceptionHandler extends BaseController{

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Result processException(Exception e){
        return error(e.getMessage());
    }

    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result processException(UnauthenticatedException e){
        return Result.UNAUTHORIZED;
    }
}
