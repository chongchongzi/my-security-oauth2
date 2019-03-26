package com.chongzi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "角色菜单分页列表Dto")
@Data
public class SysRoleMenuPageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private Integer current;

    @ApiModelProperty(value = "页数")
    private Integer size;
    
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    
    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;
}
