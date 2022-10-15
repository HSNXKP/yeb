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
import java.util.List;

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
@TableName("t_department")
@ApiModel(value = "Department对象", description = "")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    @TableField("name")
    @Excel(name = "部门")
    @NonNull
    private String name;

    @ApiModelProperty(value = "父ID")
    @TableField("parentId")
    private Integer parentId;

    @ApiModelProperty(value = "路径")
    @TableField("depPath")
    private String depPath;

    @ApiModelProperty(value = "启用禁用")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty(value = "是否父级")
    @TableField("isParent")
    private Boolean isParent;

    @ApiModelProperty(value = "子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;

    public Department(@NonNull String name) {
        this.name = name;
    }
}
