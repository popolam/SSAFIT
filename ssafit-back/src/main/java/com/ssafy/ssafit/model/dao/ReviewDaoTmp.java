package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewDaoTmp {
	// 해당 동영상의 전체 리뷰
	List<Review> selectReviewList(String videoId);
	// 해당 동영상의 리뷰 중 하나
	Review selectReviewOne(HashMap<String, Object> params);
	// 해당 동영상의 리뷰 등록, 해당 동영상의 정보를 받아서 처리할 필요가 있음.
	void insertReview(Review review);
	// 해당 동영상의 리뷰 삭제, 해당 동영상의 정보를 받아서 처리할 필요가 있음.
	int deleteReview(int reviewNo);
	// 해당 동영상의 리뷰 수정, 해당 동영상의 정보를 받아서 처리할 필요가 있음.
	int updateReview(Review review);
}
