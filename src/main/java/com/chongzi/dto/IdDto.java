package com.chongzi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "IdDto")
@Data
public class IdDto {

	@ApiModelProperty(value = "主键ID", required = true)
    private Integer id;

}