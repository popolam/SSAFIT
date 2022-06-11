package com.ssafy.ssafit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.SearchDao;
import com.ssafy.ssafit.model.dto.Search;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public List<Search> getSearchList() {
		return searchDao.selectSearchList();
	}
	@Override
	public Search getSearchByKeyword(String keyword) {
		return searchDao.selectSearchByKeyword(keyword);
	}
	
	@Override
	public void updateCnt(Search search) {
		Search originSearch = searchDao.selectSearchByKeyword(search.getKeyword());
		originSearch.setCnt(originSearch.getCnt() + 1);
		searchDao.updateSearch(originSearch);
	}
	@Override
	public void writeSearch(String keyword) {
		searchDao.insertSearch(keyword);
	}

}
