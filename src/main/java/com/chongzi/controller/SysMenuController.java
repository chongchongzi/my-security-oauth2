package com.chongzi.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongzi.common.CommonConstant;
import com.chongzi.common.R;
import com.chongzi.dto.IdDto;
import com.chongzi.dto.SysMenuPageDto;
import com.chongzi.entity.SysMenu;
import com.chongzi.enums.StateEnum;
import com.chongzi.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Api(tags="菜单权限信息")
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

	@Autowired
	private ISysMenuService sysMenuService;

	@ApiOperation(value = "查询菜单权限列表")
	@PostMapping("/list")
	public R<IPage<SysMenu>> list(@RequestBody SysMenuPageDto dto) {
		Page page = new Page();
		if (dto.getCurrent() != null) {
			page.setCurrent(dto.getCurrent());
		}
		if (dto.getSize() != null) {
			page.setSize(dto.getSize());
		}
		QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
		queryWrapper.setEntity(new SysMenu());
		if (dto.getId() != null) {
			queryWrapper.eq("id", dto.getId());
		}
		if (StrUtil.isNotEmpty(dto.getName())) {
			queryWrapper.like("name", dto.getName());
		}
		if (dto.getParentId() != null) {
			queryWrapper.eq("parentId", dto.getParentId());
		}
		if (dto.getType() != null) {
			queryWrapper.eq("type", dto.getType());
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
		return new R<>(sysMenuService.page(page, queryWrapper));
	}

	@ApiOperation(value = "查询菜单权限")
	@PostMapping("/getById")
	public R<SysMenu> getById(@RequestBody IdDto idDto) {
		SysMenu sysMenu = sysMenuService.getById(idDto.getId());
		return new R<>(sysMenu);
	}

	@ApiOperation(value = "保存菜单权限")
	@PostMapping("/save")
	public R save(@RequestBody SysMenu entity) {
		boolean res = sysMenuService.save(entity);
		if (res) {
			return new R<>(res, "保存成功");
		}
		return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "修改菜单权限")
	@PostMapping("/update")
	public R update(@RequestBody SysMenu entity) {
		SysMenu one = sysMenuService.getById(entity.getId());
		if (one == null) {
			return new R<>(Boolean.FALSE, "菜单权限不存在", CommonConstant.FAIL);
		}
		boolean res = sysMenuService.updateById(entity);
		if (res) {
			return new R<>(res, "修改成功");
		}
		return new R<>(Boolean.FALSE, "修改失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "虚拟删除菜单权限")
	@PostMapping("/remove")
	public R remove(@RequestBody IdDto idDto) {
		SysMenu obj = sysMenuService.getById(idDto.getId());
		obj.setState(StateEnum.INVALID.getStatus());
		boolean res = sysMenuService.updateById(obj);
		if (res) {
			return new R<>(res, "删除成功");
		}
		return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "物理删除菜单权限")
	@PostMapping("/delete")
	public R delete(@RequestBody IdDto dto) {
		boolean res = sysMenuService.removeById(dto.getId());
		if(res){
			return new R<>(res,"删除成功");
		}
		return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
	}
}
