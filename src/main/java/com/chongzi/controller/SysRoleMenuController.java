package com.chongzi.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongzi.common.CommonConstant;
import com.chongzi.common.R;
import com.chongzi.dto.IdDto;
import com.chongzi.dto.SysRoleMenuPageDto;
import com.chongzi.dto.SysRolePageDto;
import com.chongzi.entity.SysRole;
import com.chongzi.entity.SysRoleMenu;
import com.chongzi.enums.StateEnum;
import com.chongzi.service.ISysRoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单表 前端控制器
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Api(tags="角色菜单表信息")
@RestController
@RequestMapping("/sys-role-menu")
public class SysRoleMenuController {
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @ApiOperation(value = "查询角色菜单列表")
    @PostMapping("/list")
    public R<IPage<SysRoleMenu>> list(@RequestBody SysRoleMenuPageDto dto) {
        Page page = new Page();
        if (dto.getCurrent() != null) {
            page.setCurrent(dto.getCurrent());
        }
        if (dto.getSize() != null) {
            page.setSize(dto.getSize());
        }
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new SysRoleMenu());
        if (dto.getRoleId() != null) {
            queryWrapper.eq("roleId", dto.getRoleId());
        }
        if (dto.getMenuId() != null) {
            queryWrapper.eq("menuId", dto.getMenuId());
        }
        return new R<>(sysRoleMenuService.page(page, queryWrapper));
    }

    @ApiOperation(value = "保存角色菜单")
    @PostMapping("/save")
    public R add(@RequestBody SysRoleMenu entity) {
        boolean res = sysRoleMenuService.save(entity);
        if (res) {
            return new R<>(res, "保存成功");
        }
        return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
    }

    @ApiOperation(value = "物理删除角色菜单")
    @PostMapping("/delete")
    public R delete(@RequestBody SysRoleMenu dto) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new SysRoleMenu());
        if (dto.getRoleId() != null) {
            queryWrapper.eq("roleId", dto.getRoleId());
        }
        if (dto.getMenuId() != null) {
            queryWrapper.eq("menuId", dto.getMenuId());
        }
        boolean res = sysRoleMenuService.remove(queryWrapper);
        if(res){
            return new R<>(res,"删除成功");
        }
        return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
    }
}
