package cn.mauth.crm.util.base;

import cn.mauth.crm.util.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {

    protected final Logger log= LoggerFactory.getLogger(this.getClass());

    protected Result ok(){
        return Result.SUCCESS;
    }

    protected  <T> Result<T> ok(T t){
        return Result.success(t);
    }

    protected Result error(String message){
        return Result.error(message);
    }


}
