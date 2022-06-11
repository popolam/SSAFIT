package com.ssafy.ssafit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.MarkDao;
import com.ssafy.ssafit.model.dto.Mark;

@Service
public class MarkServiceImpl implements MarkService {

	@Autowired
	private MarkDao markDao;
	
	@Override
	public List<Mark> getMarkListByUserId(String userId) {
		return markDao.selectMarkListByUserId(userId);
	}
	
}
