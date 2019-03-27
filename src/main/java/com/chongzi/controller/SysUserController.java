package com.chongzi.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongzi.common.CommonConstant;
import com.chongzi.common.R;
import com.chongzi.dto.IdDto;
import com.chongzi.dto.SysUserDto;
import com.chongzi.dto.SysUserPageDto;
import com.chongzi.entity.SysUser;
import com.chongzi.enums.StateEnum;
import com.chongzi.security.EncodePassword;
import com.chongzi.service.ISysUserService;
import com.chongzi.utils.Base64Util;
import com.chongzi.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Api(tags="用户管理")
@RestController
@RequestMapping("/sys-user")
public class SysUserController extends ApiController {

	@Autowired
	private ISysUserService sysUserService;

	@ApiOperation(value = "查询用户信息列表")
	@PostMapping("/list")
	@Secured("ROLE_AA")
	public R<IPage<SysUser>> list(@RequestBody SysUserPageDto dto) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(userId);
		Page page = new Page();
		if (dto.getCurrent() != null) {
			page.setCurrent(dto.getCurrent());
		}
		if (dto.getSize() != null) {
			page.setSize(dto.getSize());
		}
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.setEntity(new SysUser());
		if (dto.getId() != null) {
			queryWrapper.eq("id", dto.getId());
		}
		if (StrUtil.isNotEmpty(dto.getUsername())) {
			queryWrapper.like("username", dto.getUsername());
		}
		if (StrUtil.isNotEmpty(dto.getAuthor())) {
			queryWrapper.like("author", dto.getAuthor());
		}
		if (dto.getState() != null) {
			queryWrapper.eq("state", dto.getState());
		}else{
			queryWrapper.eq("state", StateEnum.EFFECTIVE.getStatus());
		}
		queryWrapper.orderByDesc("createTime");
		return new R<>(sysUserService.page(page, queryWrapper));
	}

    @PreAuthorize("@pms.hasPermission('sys_user_view')")
	@ApiOperation(value = "根据id查询用户信息")
	@PostMapping("/getById")
	public R<SysUser> getById(@RequestBody IdDto dto) {
		SysUser sysUser = sysUserService.getById(dto.getId());
		return new R<>(sysUser);
	}

	@ApiOperation(value = "添加用户信息")
	@PostMapping("/save")
	public R save(@RequestBody SysUserDto dto) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		SysUser obj = new SysUser();
		obj.setUsername(dto.getUsername());
		queryWrapper.setEntity(obj);
		SysUser one = sysUserService.getOne(queryWrapper);
		if(one!=null) {
			return new R<>(Boolean.FALSE, "用户名已经存在", CommonConstant.FAIL);
		}
		obj.setPassword(EncodePassword.MD5(dto.getPassword()));
		obj.setAuthor(dto.getAuthor());
		obj.setState(StateEnum.EFFECTIVE.getStatus());
		obj.setCreateTime(LocalDateTime.now());
		boolean res = sysUserService.save(obj);
		if (res) {
			return new R<>(res, "保存成功");
		}
		return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "修改用户信息")
	@PostMapping("/update")
	public R update(@RequestBody SysUserDto dto) {
		SysUser one = sysUserService.getById(dto.getId());
		if (one == null) {
			return new R<>(Boolean.FALSE, "用户不存在", CommonConstant.FAIL);
		}
		if(StrUtil.isNotEmpty(dto.getPassword())){
			one.setPassword(EncodePassword.MD5(dto.getPassword()));
		}
		one.setUsername(dto.getUsername());
		one.setAuthor(dto.getAuthor());
		one.setUpdateTime(LocalDateTime.now());
		boolean res = sysUserService.updateById(one);
		if (res) {
			return new R<>(res, "修改成功");
		}
		return new R<>(Boolean.FALSE, "修改失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "修改用户密码")
	@PostMapping("/updatePassword")
	public R updatePassword(@RequestBody SysUserDto dto) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		SysUser obj = new SysUser();
		obj.setUsername(dto.getUsername());
		queryWrapper.setEntity(obj);
		SysUser one = sysUserService.getOne(queryWrapper);
		if (one == null) {
			return new R<>(Boolean.FALSE, "用户名不存在", CommonConstant.FAIL);
		}
		if(StrUtil.isEmpty(dto.getPassword())){
			return new R<>(Boolean.FALSE, "密码不能为空", CommonConstant.FAIL);
		}

		one.setPassword(EncodePassword.MD5(dto.getPassword()));
		one.setUpdateTime(LocalDateTime.now());
		boolean res = sysUserService.updateById(one);
		if (res) {
			return new R<>(res, "保存成功");
		}
		return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "虚拟删除用户")
	@PostMapping("/remove")
	public R remove(@RequestBody IdDto idDto) {
		SysUser obj = sysUserService.getById(idDto.getId());
		if(obj==null) {
			return new R<>(Boolean.TRUE, "要删除的数据不存在", CommonConstant.FAIL);
		}
		obj.setState(StateEnum.INVALID.getStatus());
		boolean res = sysUserService.updateById(obj);
		if (res) {
			return new R<>(res, "删除成功");
		}
		return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "物理删除用户")
	@PostMapping("/delete")
	public R delete(@RequestBody IdDto dto) {
		boolean res = sysUserService.removeById(dto.getId());
		if(res){
			return new R<>(res,"删除成功");
		}
		return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public R<SysUser> login(@RequestBody SysUserDto dto) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
		SysUser obj = new SysUser();
		obj.setUsername(dto.getUsername());
		obj.setState(StateEnum.EFFECTIVE.getStatus());
		queryWrapper.setEntity(obj);
		SysUser one = sysUserService.getOne(queryWrapper);
		if (one == null) {
			return new R<>(null,"用户不存在",CommonConstant.FAIL);
		}
		String pwmd5 = EncodePassword.MD5(dto.getPassword());
		if (pwmd5.equals(one.getPassword())) {
			return new R<>(null, "登录成功", CommonConstant.SUCCESS);
		}
		return new R<>(null,"密码错误",CommonConstant.FAIL);
	}

	@ApiOperation(value = "用户退出")
	@PostMapping("/logout")
	public R logout(HttpServletRequest request) {
		//前端把TOKEN清空即可
		
		return new R<>(Boolean.TRUE, "退出成功");
	}
	
}
