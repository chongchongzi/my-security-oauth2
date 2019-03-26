package com.chongzi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chongzi.entity.OauthClientDetails;
import com.chongzi.mapper.OauthClientDetailsMapper;
import com.chongzi.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * oauth客户端信息 服务实现类
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
