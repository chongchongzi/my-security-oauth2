package com.chongzi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "用户分页列表Dto")
@Data
public class SysUserPageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private Integer current;

    @ApiModelProperty(value = "页数")
    private Integer size;
    
    @ApiModelProperty(value = "主键ID")
    private Integer id;
    
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "创建人")
    private String author;

    @ApiModelProperty(value = "状态（0=无效，1=有效）")
    private Integer state;

}
