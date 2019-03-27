package com.chongzi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongzi.common.CommonConstant;
import com.chongzi.common.R;
import com.chongzi.dto.IdDto;
import com.chongzi.dto.SysRoleMenuPageDto;
import com.chongzi.dto.SysUserRolePageDto;
import com.chongzi.entity.SysRole;
import com.chongzi.entity.SysRoleMenu;
import com.chongzi.entity.SysUserRole;
import com.chongzi.enums.StateEnum;
import com.chongzi.service.ISysRoleService;
import com.chongzi.service.ISysUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Api(tags="用户角色表信息")
@RestController
@RequestMapping("/sys-user-role")
public class SysUserRoleController {
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @ApiOperation(value = "查询用户角色列表")
    @PostMapping("/list")
    public R<IPage<SysUserRole>> list(@RequestBody SysUserRolePageDto dto) {
        Page page = new Page();
        if (dto.getCurrent() != null) {
            page.setCurrent(dto.getCurrent());
        }
        if (dto.getSize() != null) {
            page.setSize(dto.getSize());
        }
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new SysUserRole());
        if (dto.getRoleId() != null) {
            queryWrapper.eq("roleId", dto.getRoleId());
        }
        if (dto.getUserId() != null) {
            queryWrapper.eq("userId", dto.getUserId());
        }
        return new R<>(sysUserRoleService.page(page, queryWrapper));
    }

    @ApiOperation(value = "保存用户角色")
    @PostMapping("/save")
    public R add(@RequestBody SysUserRole entity) {
        boolean res = sysUserRoleService.save(entity);
        if (res) {
            return new R<>(res, "保存成功");
        }
        return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
    }

    @ApiOperation(value = "物理删除用户角色")
    @PostMapping("/delete")
    public R delete(@RequestBody SysUserRole dto) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new SysUserRole());
        if (dto.getRoleId() != null) {
            queryWrapper.eq("roleId", dto.getRoleId());
        }
        if (dto.getUserId() != null) {
            queryWrapper.eq("userId", dto.getUserId());
        }
        boolean res = sysUserRoleService.remove(queryWrapper);
        if(res){
            return new R<>(res,"删除成功");
        }
        return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
    }
}
