package com.ssafy.ssafit.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Review;

public interface ReviewService {
	// 해당 비디오의 리뷰 조회
	List<Review> getReviewList(String videoId);
	// 해당 비디오의 리뷰 한개 조회
	Review getReviewOne(int reviewNo);
	// 해당 비디오의 리뷰 등록
	void writeReview(Review review);
	// 해당 비디오의 리뷰 삭제
	boolean removeReview(int reviewNo);
	// 해당 비디오의 리뷰 수정
	boolean modifyReview(Review review);
	// 해당 비디오의 리뷰 조회수 증가
	void updateCnt(int reviewNo);
}
