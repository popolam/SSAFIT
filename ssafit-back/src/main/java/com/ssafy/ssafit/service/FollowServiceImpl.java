package com.ssafy.ssafit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService {
	
	@Autowired
	private FollowDao followDao;
	
	@Override
	public List<Follow> getFollowListByUserId(String userId) {
		return followDao.selectFollowListByUserId(userId);
	}
	
}
