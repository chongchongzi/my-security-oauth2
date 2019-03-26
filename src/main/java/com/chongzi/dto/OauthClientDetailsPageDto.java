package com.chongzi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * oauth客户端信息
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@ApiModel(value = "oauth客户端信息分页列表Dto")
@Data
public class OauthClientDetailsPageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private Integer current;

    @ApiModelProperty(value = "页数")
    private Integer size;

    @ApiModelProperty(value = "客户端Id")
    private String clientId;

}
