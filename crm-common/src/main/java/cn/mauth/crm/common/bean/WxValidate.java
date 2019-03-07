package cn.mauth.crm.common.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class WxValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *微信加密签名,signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    @NotNull(message = "")
    private String signature;

    /**
     * 时间戳
     */
    @NotNull(message = "")
    private String timestamp;

    /**
     *随机数
     */
    @NotNull(message = "")
    private String nonce;

    /**
     * 随机字符串
     */
    @NotNull(message = "")
    private String echostr;
}
