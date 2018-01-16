package com.itheima.mybatis;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.pojo.User;

public class TestMyabtis {

	private SqlSessionFactory sqlSessionFactory; 
	
	@Before
	public void init() throws Exception{
		//加载核心配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这个流值得是配置文件
	}
	
	/**
	 * 成员变量上
	 * 方法内
	 * 
	 * 提出公共的信息来
	 * 工厂： 单利的  
	 * 之创建一次就可以了、下面都能用
	 * SqlSessionFactoryBuilder 来创建的工厂，创建完，还用SqlSessionFactoryBuilder不用了，自动销毁！
	 * sqlSession ： 多利， 线程不安全，每一个操作数据库的方法都要一个
	 */
	
	/**
	 * 1、根据用户id查询一个用户
	 * @throws Exception 
	 */
	@Test
	public void test() throws Exception {
		
		//SQLSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//操作数据库
		//参数说明：1、指定要执行的SQL语句的ID；2、SQL语句中的参数；
		User user = sqlSession.selectOne("test.queryUserById", 10);
		
		System.err.println(user);
		sqlSession.close();
	}
	
	/**
	 * 根据用户名称模糊查询用户列表
	 * @throws Exception
	 */
	@Test
	public void testQueryUserByUsername() throws Exception {
		
		//SQLSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<User> list = sqlSession.selectList("test.queryUserByUsername", "张");
		for (User user : list) {
			System.err.println(user);
		}
	}
	
	/**
	 * 添加用户
	 * @throws Exception
	 */
	@Test
	public void testAddUser() throws Exception {
		
		//SQLSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		User user = new User();
		user.setUsername("张翼德");
		user.setAddress("蜀国");
		user.setSex("1");
		user.setBirthday(new Date());
		sqlSession.insert("test.addUser", user);
		
		sqlSession.commit();
		System.err.println(user);
		//根据用户ID来查询订单
		
		sqlSession.close();
	}

	
	/**
	 * 更新用户
	 * @throws Exception
	 */
	@Test
	public void testUpdateUserById() throws Exception {
		
		//SQLSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		User user = new User();
		user.setId(25);
		user.setUsername("黄忠");
		sqlSession.update("test.updateUserById", user);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	/**
	 * 删除用户
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserById() throws Exception {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//这个流值得是配置文件
		//通过SQLSessionFactory创建
		//SQLSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("test.deletUserById", 22);
		sqlSession.commit();
		sqlSession.close();
	}
	
}
