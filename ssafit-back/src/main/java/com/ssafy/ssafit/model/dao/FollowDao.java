package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowDao {
	// 해당 아이디의 구독 목록
	List<Follow> selectFollowListByUserId(String userId);
}
