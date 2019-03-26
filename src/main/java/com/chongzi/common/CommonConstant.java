package com.chongzi.common;

/**
 * @Description 公共常量
 * @Author chongzi
 * @Date 2019/2/20 11:46
 * @Param
 * @return
 **/
public interface CommonConstant {
	/**
	 * 删除
	 */
	Integer STATUS_DEL = 0;
	/**
	 * 正常
	 */
	Integer STATUS_NORMAL = 1;

	/**
	 * 锁定
	 */
	Integer STATUS_LOCK = 9;

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * JSON 资源
	 */
	String CONTENT_TYPE = "application/json; charset=utf-8";


	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "dy-quality";

	/**
	 * 路由存放
	 */
	String ROUTE_KEY = "gateway_route_key";

	/**
	 * spring boot admin 事件key
	 */
	String EVENT_KEY = "event_key";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 200;
	/**
	 * 失败标记
	 */
	Integer FAIL = 500;
	
	/**
	 * 
	 */
	String MSYS_USER_PREFIX = "MSYS_USER_PREFIX";
	
	/**
	 * 用户加盐字段长度
	 */
	int USER_SALT_LENGHT = 20;
	
	/**
	 * 用户token
	 */
	String USER_TOKEN="TOKEN";
	
	/**
	 * 分隔符
	 */
	String USER_PASSWORD_SPLIT="|";

	/**
	 * 角色前缀
	 */
	String ROLE = "ROLE_";

	/**
	 * 无效的token
	 */
	int INVALID_TOKEN = 401;

	/**
	 * 访问此资源需要完全的身份验证
	 */
	int UN_LOGIN = 401;

	/**
	 * 权限不足
	 */
	int UNAUTHORIZED = 401;
}
