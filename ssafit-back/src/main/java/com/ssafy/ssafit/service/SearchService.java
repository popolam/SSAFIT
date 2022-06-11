package com.ssafy.ssafit.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Search;

public interface SearchService {
	// 조회순으로 검색어 조회
	List<Search> getSearchList();
	// 키워드로 검색어 한 개 조회
	Search getSearchByKeyword(String keyword);
	// 검색어 조회수 증가
	void updateCnt(Search search);
	// 검색어 추가
	void writeSearch(String keyword);
}
