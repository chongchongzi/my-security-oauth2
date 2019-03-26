package com.chongzi.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongzi.common.CommonConstant;
import com.chongzi.common.R;
import com.chongzi.dto.ClientIdDto;
import com.chongzi.dto.IdDto;
import com.chongzi.dto.OauthClientDetailsPageDto;
import com.chongzi.dto.SysUserDto;
import com.chongzi.entity.OauthClientDetails;
import com.chongzi.entity.SysUser;
import com.chongzi.enums.StateEnum;
import com.chongzi.security.EncodePassword;
import com.chongzi.service.IOauthClientDetailsService;
import com.chongzi.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * oauth客户端信息 前端控制器
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Api(tags="oauth客户端信息")
@RestController
@RequestMapping("/oauth-client-details")
public class OauthClientDetailsController {
    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @ApiOperation(value = "查询oauth客户端信息列表")
    @PostMapping("/list")
    public R<IPage<OauthClientDetails>> list(@RequestBody OauthClientDetailsPageDto dto) {
        Page page = new Page();
        if (dto.getCurrent() != null) {
            page.setCurrent(dto.getCurrent());
        }
        if (dto.getSize() != null) {
            page.setSize(dto.getSize());
        }
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new OauthClientDetails());
        if (dto.getClientId() != null) {
            queryWrapper.eq("client_id", dto.getClientId());
        }
        return new R<>(oauthClientDetailsService.page(page, queryWrapper));
    }

    @ApiOperation(value = "根据clientId查询oauth客户端信息")
    @PostMapping("/getByClientId")
    public R<OauthClientDetails> getById(@RequestBody ClientIdDto idDto) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new OauthClientDetails());
        queryWrapper.eq("client_id", idDto.getClientId());
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getOne(queryWrapper);
        return new R<>(oauthClientDetails);
    }

    @ApiOperation(value = "添加oauth客户端信息")
    @PostMapping("/save")
    public R save(@RequestBody OauthClientDetails dto) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new OauthClientDetails());
        queryWrapper.eq("client_id", dto.getClientId());
        OauthClientDetails one = oauthClientDetailsService.getOne(queryWrapper);
        if(one!=null) {
            return new R<>(Boolean.FALSE, "oauth客户端ID已经存在", CommonConstant.FAIL);
        }
        dto.setClientSecret(EncodePassword.MD5(dto.getClientSecret()));
        boolean res = oauthClientDetailsService.save(dto);
        if (res) {
            return new R<>(res, "保存成功");
        }
        return new R<>(Boolean.FALSE, "保存失败", CommonConstant.FAIL);
    }

    @ApiOperation(value = "修改oauth客户端信息")
    @PostMapping("/update")
    public R update(@RequestBody OauthClientDetails dto) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new OauthClientDetails());
        queryWrapper.eq("client_id", dto.getClientId());
        OauthClientDetails one = oauthClientDetailsService.getOne(queryWrapper);
        if (one == null) {
            return new R<>(Boolean.FALSE, "oauth客户端信息不存在", CommonConstant.FAIL);
        }
        if(StrUtil.isNotEmpty(dto.getClientSecret())){
            dto.setClientSecret(EncodePassword.MD5(dto.getClientSecret()));
        }
        boolean res = oauthClientDetailsService.update(dto,queryWrapper);
        if (res) {
            return new R<>(res, "修改成功");
        }
        return new R<>(Boolean.FALSE, "修改失败", CommonConstant.FAIL);
    }


    @ApiOperation(value = "物理删除oauth客户端信息")
    @PostMapping("/delete")
    public R delete(@RequestBody ClientIdDto dto) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(new OauthClientDetails());
        queryWrapper.eq("client_id", dto.getClientId());
        boolean res = oauthClientDetailsService.remove(queryWrapper);
        if(res){
            return new R<>(res,"删除成功");
        }
        return new R<>(Boolean.FALSE, "删除失败", CommonConstant.FAIL);
    }

}
