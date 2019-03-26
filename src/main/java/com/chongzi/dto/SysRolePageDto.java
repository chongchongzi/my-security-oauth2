package com.chongzi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "系统角色分页列表Dto")
@Data
public class SysRolePageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private Integer current;

    @ApiModelProperty(value = "页数")
    private Integer size;
    
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "创建人")
    private String author;

    @ApiModelProperty(value = "状态（0=无效，1=有效）")
    private Integer state;

}
