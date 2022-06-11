package com.ssafy.ssafit.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.Figure;

public interface FigureService {
	// 파일 하나 조회
	Figure getFigureOne(String userId);
	// 파일 정보 추가
	void writeFigure(String userId);
	// 파일 정보 수정
	boolean modifyFigure(Figure figure);
}
