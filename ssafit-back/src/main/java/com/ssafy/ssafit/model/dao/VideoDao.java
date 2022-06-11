package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoDao {
	// 전체 비디오
	List<Video> selectVideoList();
	// 부위별 비디오
//	List<Video> selectVideoListByTag(String tag);
	List<HashMap<String, Object>> selectVideoListByTag(String tag);
	List<HashMap<String, Object>> selectVideoListWithStatusByTag(HashMap<String, String> params);
	// 부위별 비디오 원하는 개수 조회
	List<Video> selectVideoListByKeyword(HashMap<String, Object> params);
	// 비디오 한 개
	Video selectVideoOne(String videoId);
	HashMap<String, Object> selectVideoOneWithStatus(HashMap<String, String> param);
	// 비디오 한 개 수정
	int updateVideo(Video video);
	// 해당 유저의 찜한 동영상 리스트
	List<Video> selectMarkListByUserId(String userId);
	// 해당 유저가 해당 동영상을 찜 했는가
	int selectMark(HashMap<String, String> params);
	// 해당 유저의 찜 동영상 추가
	void insertMark(HashMap<String, String> params);
	// 해당 유저의 찝 동영상 삭제
	int deleteMark(HashMap<String, String> params);
	// 해당 유저의 구독 목록
	List<Video> selectFollowListByUserId(String userId);
	// 해당 유저가 해당 채널을 구독 했는가
	int selectFollow(HashMap<String, String> params);
	// 해당 유저의 구독 채널 추가
	void insertFollow(HashMap<String, String> params);
	// 해당 유저의 구독 채널 삭제
	int deleteFollow(HashMap<String, String> params);
}
