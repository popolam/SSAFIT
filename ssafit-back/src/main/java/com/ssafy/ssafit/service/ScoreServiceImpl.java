package com.ssafy.ssafit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ScoreDao;
import com.ssafy.ssafit.model.dto.Score;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreDao scoreDao;
	
	@Override
	public List<Score> getScoreList() {
		return scoreDao.selectScoreList();
	}

	@Override
	public List<Score> getScoreListByArea(String areaName) {
		return scoreDao.selectScoreListByArea(areaName);
	}

	@Override
	public Score getScoreByRank(int no) {
		List<Score> list = scoreDao.selectScoreList();
		list.add(0, new Score());
		return list.get(no);
	}

}
