package com.chongzi.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongzi.common.CommonConstant;
import com.chongzi.entity.*;
import com.chongzi.mapper.SysUserMapper;
import com.chongzi.service.*;
import com.chongzi.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private ISysRoleService sysRoleService;

	@Autowired
	private ISysMenuService sysMenuService;

	@Autowired
	private ISysUserRoleService sysUserRoleService;

	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	
	@Override
	public boolean isloginSuccess(String token) {
		if (StrUtil.isEmpty(token)) {
			return false;
		}
		String[] split = token.split("\\" + CommonConstant.USER_PASSWORD_SPLIT);
		String username = split[0];
		String password = split[1];

		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		SysUser obj = new SysUser();
		obj.setUsername(username);
		obj.setPassword(password);
		queryWrapper.setEntity(obj);
		return sysUserMapper.selectOne(queryWrapper) != null;
	}

	@Override
	public SysUserVo getSysUserVo(String username) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		SysUser obj = new SysUser();
		obj.setUsername(username);
		queryWrapper.setEntity(obj);
		SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
		if (sysUser == null) {
			return null;
		}
		//设置角色列表
		List<String> roleCodes = new ArrayList<>();
		//设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();

		QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(sysUser.getId());
		sysUserRoleQueryWrapper.setEntity(sysUserRole);
		List<SysUserRole> roleUserList = sysUserRoleService.list(sysUserRoleQueryWrapper);
		for (int i = 0; i < roleUserList.size(); i++) {
			sysUserRole = roleUserList.get(i);
			SysRole sysRole = sysRoleService.getById(sysUserRole.getRoleId());
			roleCodes.add(sysRole.getCode());

			QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper = new QueryWrapper<>();
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setRoleId(sysRole.getId());
			sysRoleMenuQueryWrapper.setEntity(sysRoleMenu);
			List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(sysRoleMenuQueryWrapper);
			for (int j = 0; j < roleMenuList.size(); j++) {
				sysRoleMenu = roleMenuList.get(j);
				SysMenu sysMenu = sysMenuService.getById(sysRoleMenu.getMenuId());
				permissions.add(sysMenu.getPermission());
			}
		}

		SysUserVo sysUserVo = new SysUserVo();
		sysUserVo.setSysUser(sysUser);
		sysUserVo.setRoles(ArrayUtil.toArray(roleCodes, String.class));
		sysUserVo.setPermissions(ArrayUtil.toArray(permissions, String.class));
		return sysUserVo;
	}

}
