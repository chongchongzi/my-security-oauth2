package com.chongzi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "菜单权限列表Dto")
@Data
public class SysMenuPageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private Integer current;

    @ApiModelProperty(value = "页数")
    private Integer size;
    
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父菜单ID")
    private Integer parentId;

    @ApiModelProperty(value = "菜单类型（1=菜单,2=按钮）")
    private Integer type;

    @ApiModelProperty(value = "创建人")
    private String author;

    @ApiModelProperty(value = "状态（0=无效，1=有效）")
    private Integer state;

}
