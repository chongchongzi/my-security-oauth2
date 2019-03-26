package com.chongzi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "菜单权限")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 菜单权限标识
     */
    @ApiModelProperty(value = "")
    private String permission;

    /**
     * 前端URL
     */
    @ApiModelProperty(value = "前端URL")
    private String path;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    @TableField("parentId")
    private Integer parentId;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * VUE页面
     */
    @ApiModelProperty(value = "VUE页面")
    private String component;

    /**
     * 排序值
     */
    @ApiModelProperty(value = "")
    private Integer sort;

    /**
     * 菜单类型（1=菜单,2=按钮）
     */
    @ApiModelProperty(value = "菜单类型（1=菜单,2=按钮）")
    private Integer type;

    /**
     * 状态(1=正常，0=删除)
     */
    @ApiModelProperty(value = "状态(1=正常，0=删除)")
    private Integer state;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String author;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("updateTime")
    private Date updateTime;


}
