package com.wdd.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

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
@TableName("t_politics_status")
@ApiModel(value = "PoliticsStatus对象", description = "")
public class PoliticsStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("政治面貌")
    @TableField("name")
    @Excel(name = "政治面貌")
    @NonNull
    private String name;

    public PoliticsStatus(@NonNull String name) {
        this.name = name;
    }
}
