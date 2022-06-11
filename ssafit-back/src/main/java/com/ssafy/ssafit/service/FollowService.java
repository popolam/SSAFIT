package com.ssafy.ssafit.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowService {
	// 해당 아이디의 구독 채널 조회
	List<Follow> getFollowListByUserId(String userId);
}
