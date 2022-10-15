package com.wdd.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: wdd
 * @date: 2022/9/26 15:32
 */
@Getter
@Setter
@TableName("t_menu_role")
@ApiModel(value = "RoleMenu对象", description = "")
public class MenuRole {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty("菜单ID")
    @TableField("mid")
    private Integer menuId;


    @ApiModelProperty("角色ID")
    @TableField("rid")
    private Integer roleId;

}
