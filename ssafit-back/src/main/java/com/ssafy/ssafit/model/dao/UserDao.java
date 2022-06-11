package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.User;

public interface UserDao {
	// user 한 개 조회
	User selectUserById(String id);
	// user 등록
	void insertUser(User user);
	// score 초기 등록
	void insertScore(User user);
	// user 수정
	int updateUser(User user);
}
