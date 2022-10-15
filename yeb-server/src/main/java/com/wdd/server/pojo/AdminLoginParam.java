package com.wdd.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: wdd
 * @date: 2022/9/23 9:38
 */

@Data
@ApiModel(value = "AdminLoginParam对象")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)  //禁用hashcode()
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "验证码",required = true)
    private String code;

}
