package com.wdd.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@TableName("t_salary")
@ApiModel(value = "Salary对象", description = "")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("基本工资")
    @TableField("basicSalary")
    private BigDecimal basicSalary;

    @ApiModelProperty("奖金")
    @TableField("bonus")
    private BigDecimal bonus;

    @ApiModelProperty("午餐补助")
    @TableField("lunchSalary")
    private BigDecimal lunchSalary;

    @ApiModelProperty("交通补助")
    @TableField("trafficSalary")
    private BigDecimal trafficSalary;

    @ApiModelProperty("应发工资")
    @TableField("allSalary")
    private BigDecimal allSalary;

    @ApiModelProperty("养老金基数")
    @TableField("pensionBase")
    private Integer pensionBase;

    @ApiModelProperty("养老金比率")
    @TableField("pensionPer")
    private Float pensionPer;

    @ApiModelProperty("启用时间")
    @TableField("createDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDateTime createDate;

    @ApiModelProperty("医疗基数")
    @TableField("medicalBase")
    private Integer medicalBase;

    @ApiModelProperty("医疗比率")
    @TableField("medicalPer")
    private Float medicalPer;

    @ApiModelProperty("公积金基数")
    @TableField("accumulationFundBase")
    private Integer accumulationFundBase;

    @ApiModelProperty("公积金比率")
    @TableField("accumulationFundPer")
    private Float accumulationFundPer;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;


}
