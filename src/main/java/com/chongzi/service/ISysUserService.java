package com.chongzi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chongzi.entity.SysUser;
import com.chongzi.vo.SysUserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chongzi
 * @since 2019-02-21
 */
public interface ISysUserService extends IService<SysUser> {


	/**
	 * 是否登录成功
	 * @param token
	 * @return ：true:成功,false:不成功
	 */
	boolean isloginSuccess(String token) ;

	SysUserVo getSysUserVo(String username);
	
}
