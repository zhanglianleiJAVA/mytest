package com.itheima.mybatis.mapper;

import java.util.List;

import com.itheima.pojo.User;

public interface UserMapper {

	/**
	 * 开发规范在接口上加注释：
	 * 根据用户id查询一个用户信息
	 * @param id
	 * @return
	 */
	public User queryUserById(Integer id);
	
	/**
	 * 根据用户名称模糊查询用户信息列表
	 * @param username
	 * @return
	 */
	public List<User> queryUserByUserName(String username);
	
	/**
	 * 添加用户信息
	 * @param user
	 */
	public void addUser(User user);
	
}
