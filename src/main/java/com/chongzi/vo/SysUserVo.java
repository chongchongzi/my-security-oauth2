package com.chongzi.vo;

import com.chongzi.entity.SysUser;

import java.io.Serializable;

/**
 * @Description
 * @Author chongzi
 * @Date 2019/3/20 18:13
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 **/
public class SysUserVo implements Serializable {

	private static final long serialVersionUID = -4379972969787452441L;
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private String[] roles;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
