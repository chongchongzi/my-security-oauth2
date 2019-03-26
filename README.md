## 项目准备

1. 导入SQL
	```file
	my-security-oauth2/src/main/resources/sql/my-security-oauth2.sql
	```
2. 修改application-dev.properties下的mysql连接信息
3. 启动com.chongzi.MyApplication
4. 访问http://127.0.0.1:8080/swagger-ui.html
5. 通过接口或者以下SQL添加用户，角色，菜单，用户角色，角色菜单，oauth客户端信息等相关信息
 ```sql
  INSERT INTO `oauth_client_details` VALUES ('1', null, 'c81e728d9d4c2f636f067f89cc14862c', 'app,openid', 'password,authorization_code', 'https://www.baidu.com', null, null, null, null, '');
  INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, '1', 'admin', '2019-03-26 09:31:52', null);
  INSERT INTO `sys_role` VALUES ('1', '测试角色', 'ROLE_AA', 'ROLE_AA', '1', 'chongzi', '2019-03-25 19:52:34', '2019-03-25 19:52:37');
  INSERT INTO `sys_user_role` VALUES ('1', '1');
  INSERT INTO `sys_menu` VALUES ('1', '测试菜单', 'ROLE_AA', 'ROLE_AA', '0', 'ROLE_AA', 'ROLE_AA', '1', '1', '1', '虫子', '2019-03-25 19:53:19', '2019-03-25 19:53:21');
  INSERT INTO `sys_role_menu` VALUES ('1', '1');
 ```
7.访问http://127.0.0.1:8080/oauth/token?grant_type=password&username=admin&password=123456&client_id=1&client_secret=2
```json
{
    "access_token": "34db999e-0840-4c82-91b7-d5040d7ffc4e",
    "token_type": "bearer",
    "expires_in": 43041,
    "scope": "app openid"
}
```
8.访问http://127.0.0.1:8080/sys-user/list
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "records": [
            {
                "id": 1,
                "username": "admin",
                "password": "e10adc3949ba59abbe56e057f20f883e",
                "salt": null,
                "phone": null,
                "avatar": null,
                "deptId": null,
                "state": 1,
                "author": "admin",
                "createTime": "2019-03-26 09:31:52",
                "updateTime": null
            }
        ],
        "total": 2,
        "size": 10,
        "current": 1,
        "searchCount": true,
        "pages": 1
    }
}
```

## 更多`OAuth2`请参考
> [理解OAuth 2.0](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html) 或者 
> [spring-oauth-server 数据库表说明](http://andaily.com/spring-oauth-server/db_table_description.html) 或者
> [springboot+spring security +oauth2.0 demo搭建（password模式）（认证授权端与资源服务端分离的形式）](https://www.cnblogs.com/hetutu-5238/p/10022963.html)