package com.bookroom.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookroom.user.dao.UserDao;
import com.bookroom.user.service.UserService;
import com.bookroom.user.vo.UserVo;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void insertUser(UserVo userVo) {
		userDao.userInsert(userVo);
	}

}
