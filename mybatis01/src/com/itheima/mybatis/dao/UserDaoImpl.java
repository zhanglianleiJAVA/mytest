package com.itheima.mybatis.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.itheima.pojo.User;

public class UserDaoImpl implements UserDao {

	private SqlSessionFactory sqlSessionFactory;
	
	//set/构造方法注入
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public User queryUserById(Integer id) {
		
		//SqlSession来操作数据库
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.queryUserById", id);
		sqlSession.close();
		return user;
	}

	@Override
	public List<User> queryUserListByUserName(String username) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		List<User> list = sqlSession.selectList("test.queryUserByUsername", username);
		sqlSession.close();
		
		return list;
	}

	@Override
	public void addUser(User user) {
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		sqlSession.insert("test.addUser", user);
		sqlSession.commit();
		sqlSession.close();
	}

}
