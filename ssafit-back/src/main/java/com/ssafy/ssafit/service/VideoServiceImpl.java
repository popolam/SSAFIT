package com.ssafy.ssafit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;
	
	@Override
	public List<Video> getVideoList() {
		return videoDao.selectVideoList();
	}

//	@Override
//	public List<Video> getVideoListByTag(String tag) {
//		return videoDao.selectVideoListByTag(tag);
//	}
	public List<HashMap<String, Object>> getVideoListByTag(String tag) {
		return videoDao.selectVideoListByTag(tag);
	}
	public List<HashMap<String, Object>> getVideoListWithStatusByTag(HashMap<String, String> param) {
		return videoDao.selectVideoListWithStatusByTag(param);
	}

	@Override
	public List<Video> getVideoListByKeyword(HashMap<String, Object> params) {
		return videoDao.selectVideoListByKeyword(params);
	}
	
	@Override
	public Video getVideoOne(String videoId) {
		this.updateCnt(videoId);
		return videoDao.selectVideoOne(videoId);
	}
	@Override
	public HashMap<String, Object> getVideoOneWithStatus(HashMap<String, String> param) {
		System.out.println("테스트1======================");
		System.out.println(param.toString());
		this.updateCnt(param.get("videoId"));
		return videoDao.selectVideoOneWithStatus(param);
	}
	

	@Override
	public void updateCnt(String videoId) {
		System.out.println("[videoId] : ");
		System.out.println(videoId);
		Video video = videoDao.selectVideoOne(videoId);
		System.out.println("[video] : ");
		System.out.println(video.toString());
		video.setViewCnt(video.getViewCnt() + 1);
		System.out.println(video.getViewCnt());
		System.out.println("video : " + video);
		videoDao.updateVideo(video);
	}

	@Override
	public List<Video> getMarkListByUserId(String userId) {
		return videoDao.selectMarkListByUserId(userId);
	}
	
	@Override
	public int getMark(HashMap<String, String> params) {
		return videoDao.selectMark(params);
	}

	@Override
	public void writeMark(HashMap<String, String> params) {
		videoDao.insertMark(params);
	}

	@Override
	public boolean removeMark(HashMap<String, String> params) {
		return videoDao.deleteMark(params) == 1;
	}

	@Override
	public List<Video> getFollowListByUserId(String userId) {
		return videoDao.selectFollowListByUserId(userId);
	}
	
	@Override
	public void writeFollow(HashMap<String, String> params) {
		videoDao.insertFollow(params);
	}

	@Override
	public boolean removeFollow(HashMap<String, String> params) {
		return videoDao.deleteFollow(params) == 1;
	}

	@Override
	public int getFollow(HashMap<String, String> params) {
		return videoDao.selectFollow(params);
	}

	
}
