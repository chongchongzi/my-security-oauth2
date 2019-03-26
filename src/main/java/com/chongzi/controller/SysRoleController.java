package com.chongzi.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongzi.common.CommonConstant;
import com.chongzi.common.R;
import com.chongzi.dto.IdDto;
import com.chongzi.dto.SysRolePageDto;
import com.chongzi.entity.SysRole;
import com.chongzi.enums.StateEnum;
import com.chongzi.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Api(tags="系统角色信息")
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

	@Autowired
	private ISysRoleService sysRoleService;

	@ApiOperation(value = "查询角色列表")
	@PostMapping("/list")
	public R<IPage<SysRole>> list(@RequestBody SysRolePageDto dto) {
		Page page = new Page();
		if (dto.getCurrent() != null) {
			page.setCurrent(dto.getCurrent());
		}
		if (dto.getSize() != null) {
			page.setSize(dto.getSize());
		}
		QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.setEntity(new SysRole());
		if (dto.getId() != null) {
			queryWrapper.eq("id", dto.getId());
		}
		if (StrUtil.isNotEmpty(dto.getName())) {
			queryWrapper.like("name", dto.getName());
		}
		if (StrUtil.isNotEmpty(dto.getCode())) {
			queryWrapper.like("code", dto.getCode());
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
		return new R<>(sysRoleService.page(page, queryWrapper));
	}

	@ApiOperation(value = "根据id查询角色")
	@PostMapping("/getById")
	public R<SysRole> getById(@RequestBody IdDto idDto) {
		return new R<>(sysRoleService.getById(idDto.getId()));
	}

	@ApiOperation(value = "保存角色")
	@PostMapping("/save")
	public R add(@RequestBody SysRole entity) {
		boolean res = sysRoleService.save(entity);
		if (res) {
			return new R<>(res, "保存成功");
		}
		return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "修改角色")
	@PostMapping("/update")
	public R update(@RequestBody SysRole entity) {
		SysRole one = sysRoleService.getById(entity.getId());
		if (one == null) {
			return new R<>(Boolean.FALSE, "角色不存在", CommonConstant.FAIL);
		}
		boolean res = sysRoleService.updateById(entity);
		if (res) {
			return new R<>(res, "修改成功");
		}
		return new R<>(Boolean.FALSE, "修改失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "虚拟删除角色")
	@PostMapping("/remove")
	public R remove(@RequestBody IdDto idDto) {
		SysRole obj = sysRoleService.getById(idDto.getId());
		obj.setState(StateEnum.INVALID.getStatus());
		boolean res = sysRoleService.updateById(obj);
		if (res) {
			return new R<>(res, "删除成功");
		}
		return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
	}

	@ApiOperation(value = "物理删除角色")
	@PostMapping("/delete")
	public R delete(@RequestBody IdDto dto) {
		boolean res = sysRoleService.removeById(dto.getId());
		if(res){
			return new R<>(res,"删除成功");
		}
		return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
	}
}
