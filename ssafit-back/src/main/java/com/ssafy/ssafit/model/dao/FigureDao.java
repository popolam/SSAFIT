package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Figure;

public interface FigureDao {
	// 파일 조회
	Figure selectFigureOne(String userId);
	// 파일 테이블에 userId 추가
	void insertFigure(String userId);
	// 파일 테이블 url, name 수정
	int updateFigure(Figure figure);
}
