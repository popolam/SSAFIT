package com.ssafy.ssafit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(String id) {
		return userDao.selectUserById(id);
	}

	@Override
	public void join(User user) {
		userDao.insertUser(user);
		userDao.insertScore(user);
	}

	@Override
	public User myPage(String id) {
		return userDao.selectUserById(id);
	}

	@Override
	public void modifyUser(User user) {
		userDao.updateUser(user);
	}

}
