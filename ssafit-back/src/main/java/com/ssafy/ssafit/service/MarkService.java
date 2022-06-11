package com.ssafy.ssafit.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Mark;

public interface MarkService {
	// 해당 아이디의 찜한 동영상 목록
	List<Mark> getMarkListByUserId(String userId);
}
