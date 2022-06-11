package com.ssafy.ssafit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<Review> getReviewList(String videoId) {
		return reviewDao.selectReviewList(videoId);
	}
	
	@Override
	public Review getReviewOne(int reviewNo) {
		return reviewDao.selectReviewOne(reviewNo);
	}

	@Override
	public void writeReview(Review review) {
		reviewDao.insertReview(review);
	}

	@Override
	public boolean removeReview(int reviewNo) {
		return reviewDao.deleteReview(reviewNo) == 1;
	}

	@Override
	public boolean modifyReview(Review review) {
		System.out.println(review.getContent() + "=====================================================");
		System.out.println(review.getReviewNo() + "=====================================================");
		Review originReivew = reviewDao.selectReviewOne(review.getReviewNo());
		originReivew.setContent(review.getContent());
		return reviewDao.updateReview(originReivew) == 1;
	}

	@Override
	public void updateCnt(int reviewNo) {
//		Review review = reviewDao.selectReviewOne(reviewNo);
//		reviewDao.updateReview(review);
	}
}
