package com.chongzi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: AuthenticationManagerBuilder中的AuthenticationProvider是进行认证的核心
 * @Author chongzi
 * @Date 2019/3/25 10:57
 */
@Component("myAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "myUserDetailService")
    private UserDetailsService userDetailsService;

   public static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    /**
     *authentication是前台拿过来的号码密码bean  主要验证流程代码  注意这儿懒得用加密验证！！！
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.info("用户输入的用户名是：" + authentication.getName());
        LOGGER.info("用户输入的密码是：" + authentication.getCredentials());
        // 根据用户输入的用户名获取该用户名已经在服务器上存在的用户详情，如果没有则返回null
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        try {
            LOGGER.info("服务器上已经保存的用户名是：" + userDetails.getUsername());
            LOGGER.info("服务器上保存的该用户名对应的密码是： " + userDetails.getPassword());
            LOGGER.info("服务器上保存的该用户对应的权限是：" + userDetails.getAuthorities());
            if(authentication.getCredentials().equals(userDetails.getPassword())){
                //验证成功  将返回一个UsernamePasswordAuthenticaionToken对象
                LOGGER.info("LOGIN SUCCESS !!!!!!!!!!!!!!!!!!!");
                //分别返回用户实体   输入的密码   以及用户的权限
                return new UsernamePasswordAuthenticationToken(userDetails,authentication.getCredentials(),userDetails.getAuthorities());
            }
        } catch (Exception e){
            LOGGER.error("author failed, -------------------the error message is:-------- " + e);
            throw e;
        }
        //如果验证不同过则返回null或者抛出异常
        return null;
    }

    /**
     *
     **/
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}