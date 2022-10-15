package com.wdd.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "name")
@TableName("t_joblevel")
@ApiModel(value = "Joblevel对象", description = "")
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("职称名称")
    @TableField("name")
    @Excel(name = "职称")
    @NonNull
    private String name;

    @ApiModelProperty("职称等级")
    @TableField("titleLevel")
    private String titleLevel;

    @ApiModelProperty("创建时间")
    @TableField("createDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Shanghai")
    private LocalDateTime createDate;

    @ApiModelProperty("启用禁用")
    @TableField("enabled")
    private Boolean enabled;

    public Joblevel(@NonNull String name) {
        this.name = name;
    }
}
