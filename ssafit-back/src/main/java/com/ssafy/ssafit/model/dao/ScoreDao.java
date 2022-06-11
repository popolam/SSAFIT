package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Score;

public interface ScoreDao {
	// 전체 점수 조회
	List<Score> selectScoreList();
	// 지역별 점수 조회
	List<Score> selectScoreListByArea(String areaName);
}
