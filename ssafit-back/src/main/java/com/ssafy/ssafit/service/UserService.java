package com.ssafy.ssafit.service;

import com.ssafy.ssafit.model.dto.User;

public interface UserService {
	// 로그인
	User login(String id);
	// 회원가입
	void join(User user);
	// 마이페이지
	User myPage(String id);
	// 유저 정보 수정
	void modifyUser(User user);
}
