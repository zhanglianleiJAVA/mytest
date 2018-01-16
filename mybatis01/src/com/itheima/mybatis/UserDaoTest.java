package com.itheima.mybatis;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.mybatis.dao.UserDaoImpl;
import com.itheima.pojo.User;

public class UserDaoTest {

	private SqlSessionFactory sqlSessionFactory; 
	
	@Before
	public void init() throws Exception{
		//加载核心配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这个流值得是配置文件
	}
	
	@Test
	public void testQueryUserById() {
		UserDaoImpl daoImpl = new UserDaoImpl(sqlSessionFactory);
		User user = daoImpl.queryUserById(10);
		System.err.println(user);
	}

	@Test
	public void testQueryUserListByUserName() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

}
