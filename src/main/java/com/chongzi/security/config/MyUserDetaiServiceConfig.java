package com.chongzi.security.config;

import cn.hutool.core.util.ArrayUtil;
import com.chongzi.common.CommonConstant;
import com.chongzi.entity.SysUser;
import com.chongzi.enums.StateEnum;
import com.chongzi.service.ISysUserService;
import com.chongzi.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author chongzi
 * @Date 2019/3/25 10:57
 */
@Component("myUserDetailService")
public class MyUserDetaiServiceConfig implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查找用户信息
        SysUserVo info = sysUserService.getSysUserVo(username);
        if (info == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(CommonConstant.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();
        //根据查找到的用户信息判断用户是否被冻结
        boolean enabled = true;
        if(user.getState() == StateEnum.INVALID.getStatus()){
            enabled = false;
        }
        return new User(username,user.getPassword(),enabled, true, true, true,authorities);
    }
}