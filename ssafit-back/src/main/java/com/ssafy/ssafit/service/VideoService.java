package com.ssafy.ssafit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {
	// 모든 비디오 조회
	List<Video> getVideoList();
	// 부위별 비디오 조회
//	List<Video> getVideoListByTag(String tag);
	List<HashMap<String, Object>> getVideoListByTag(String tag);
	List<HashMap<String, Object>> getVideoListWithStatusByTag(HashMap<String, String> param);
	// 부위별 비디오 원하는 개수 조회
	List<Video> getVideoListByKeyword(HashMap<String, Object> params);
	// 비디오 한 개 조회
	Video getVideoOne(String videoId);
	HashMap<String, Object> getVideoOneWithStatus(HashMap<String, String> param);
	// 비디오의 조회수 증가
	void updateCnt(String videoId);
	// 해당 아이디의 찜한 동영상 목록
	List<Video> getMarkListByUserId(String userId);
	// 해당 아이디의 해당 동영상을 찜 유무
	int getMark(HashMap<String, String> params);
	// 해당 아이디의 찜 동영상 추가
	void writeMark(HashMap<String, String> params);
	// 해당 아이디의 찜 동영상 삭제
	boolean removeMark(HashMap<String, String> params);
	// 해당 아이디의 구독 채널 조회
	List<Video> getFollowListByUserId(String userId);
	// 해당 아이디의 해당 채널을 구독 유무
	int getFollow(HashMap<String, String> params);
	// 해당 아이디의 구독 채널 추가
	void writeFollow(HashMap<String, String> params);
	// 해당 아이디의 구독 채널 삭제
	boolean removeFollow(HashMap<String, String> params);
}
