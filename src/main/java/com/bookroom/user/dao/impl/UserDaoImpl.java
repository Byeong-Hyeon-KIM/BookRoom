package com.bookroom.user.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookroom.user.dao.UserDao;
import com.bookroom.user.vo.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private  SqlSession   sqlSession;
	
	@Override
	public void userInsert(UserVo userVo) {
		sqlSession.insert("User.UserInsert", userVo);
	}

}
