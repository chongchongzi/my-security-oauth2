package com.chongzi.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "客户端IdDto")
@Data
public class ClientIdDto {

	@ApiModelProperty(value = "客户端Id", required = true)
    private String clientId;

}