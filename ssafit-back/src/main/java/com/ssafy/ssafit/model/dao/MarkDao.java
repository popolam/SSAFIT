package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Mark;

public interface MarkDao {
	// 해당 유저의 찜한 동영상 리스트
	List<Mark> selectMarkListByUserId(String userId);
}
