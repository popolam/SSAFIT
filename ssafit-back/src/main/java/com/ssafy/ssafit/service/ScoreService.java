package com.ssafy.ssafit.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Score;

public interface ScoreService {
	// 높은 순서대로 전체 조회
	List<Score> getScoreList();
	// 지역별로 총점이 높은 순서대로 조회
	List<Score> getScoreListByArea(String areaName);
	// 등수로 지역과 반 조회
	Score getScoreByRank(int no);
}
