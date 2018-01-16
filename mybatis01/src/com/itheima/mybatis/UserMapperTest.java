package com.itheima.mybatis;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.mybatis.mapper.UserMapper;
import com.itheima.pojo.User;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory; 
	
	@Before
	public void init() throws Exception{
		//加载核心配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这个流值得是配置文件
	}
	
	/**
	 * 根据用户ID来查询用户信息的
	 */
	@Test
	public void testQueryUserById() {
		//sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SQLSession来获取Mapper接口的代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.queryUserById(10);
		System.err.println(user);
	}

	@Test
	public void testQueryUserByUserName() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SQLSession来获取Mapper接口的代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.queryUserByUserName("张");
		for (User user : list) {
			System.err.println(user);
		}
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

}
