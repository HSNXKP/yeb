package com.wdd.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Getter
@Setter
@TableName("t_appraise")
@ApiModel(value = "Appraise对象", description = "")
public class Appraise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工ID")
    @TableField("eid")
    private Integer eid;

    @ApiModelProperty("考评日期")
    @TableField("app_date")
    private LocalDate appDate;

    @ApiModelProperty("考评结果")
    @TableField("app_result")
    private String appResult;

    @ApiModelProperty("考评内容")
    @TableField("app_content")
    private String appContent;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


}
