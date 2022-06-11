package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Search;

public interface SearchDao {
	// 조회순으로 검색어 조회
	List<Search> selectSearchList();
	// 검색어 한 개 조회
	Search selectSearchByKeyword(String keyword);
	// 검색어 조회수 증가
	int updateSearch(Search search);
	// 검색어 추가
	void insertSearch(String keyword);
}
