package cn.mauth.crm.util.common;

import org.apache.commons.lang.StringUtils;

public class NumUtil {

    public static boolean toLong(String data){
        if(StringUtils.isEmpty(data))
            return false;
        try {
            Long.valueOf(data);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
