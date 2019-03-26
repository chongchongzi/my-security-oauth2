/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.chongzi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chongzi
 * @date 2018/9/30
 * 数据状态
 */
@Getter
@AllArgsConstructor
public enum StateEnum {
	/**
	 * 无效
	 */
	INVALID(0, "无效"),

	/**
	 * 有效
	 */
	EFFECTIVE(1, "有效");

	/**
	 * 类型
	 */
	private final int status;
	/**
	 * 描述
	 */
	private final String description;

	public static String getDescription(Integer value) {
		if (value == null) {
			return "";
		} else {
			for (StateEnum ms : StateEnum.values()) {
				if (ms.getStatus() == value.intValue()) {
					return ms.getDescription();
				}
			}
			return "";
		}
	}
}
