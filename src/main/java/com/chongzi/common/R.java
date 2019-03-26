package com.chongzi.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 响应信息主体
 * @Author chongzi
 * @Date 2019/2/20 11:48
 * @Param
 * @return
 **/
@ApiModel(value = "响应信息主体")
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "响应编码")
	@Getter
	@Setter
	private int code = CommonConstant.SUCCESS;

	@ApiModelProperty(value = "响应信息")
	@Getter
	@Setter
	private String msg = "success";

	@ApiModelProperty(value = "响应数据")
	@Getter
	@Setter
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}
	
	public R(T data, String msg, int code) {
		super();
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstant.FAIL;
	}
}
