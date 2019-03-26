package com.chongzi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "用户角色")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID" , required = true)
    @TableId("userId")
    private Integer userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID" , required = true)
    @TableField("roleId")
    private Integer roleId;


}
