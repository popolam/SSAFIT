package com.ssafy.ssafit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.exception.ReviewNotFoundException;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/review/{videoId}")
	public ResponseEntity<List<Review>> list(@PathVariable String videoId) {
		return new ResponseEntity<List<Review>>(reviewService.getReviewList(videoId), HttpStatus.OK);
	}
	
	@GetMapping("/review/one/{reviewNo}")
	public ResponseEntity<Review> one(@PathVariable int reviewNo) {
		try {
			return new ResponseEntity<Review>(reviewService.getReviewOne(reviewNo), HttpStatus.OK);
		} catch(Exception e) {
			throw new ReviewNotFoundException("리뷰가 없습니다.");
		}
	}
	
//	@GetMapping("/review/{videoId}/{reviewNo}")
//	public ResponseEntity<Review> one(@PathVariable String videoId, @PathVariable String reviewNo) {
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		params.put("videoId", videoId);
//		params.put("reviewNo", reviewNo);
//		return new ResponseEntity<Review>(reviewService.getReviewOne(params), HttpStatus.OK);
//	}
	
	@PostMapping("/review")
	public ResponseEntity<List<Review>> write(@RequestBody Review review) {
		reviewService.writeReview(review);
		return new ResponseEntity<List<Review>>(reviewService.getReviewList(review.getVideoId()), HttpStatus.CREATED);
	}
	
//	@DeleteMapping("/review/delete/{reviewNo}")
//	public ResponseEntity<String> delete(@PathVariable int reviewNo) {
//		if(reviewService.removeReview(reviewNo)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
	@DeleteMapping("/review")
	public ResponseEntity<List<Review>> delete(@RequestBody Map<String, Object> payload) {
		reviewService.removeReview(Integer.parseInt(payload.get("reviewNo").toString()));
		return new ResponseEntity<List<Review>>(reviewService.getReviewList(payload.get("videoId").toString()), HttpStatus.OK);
	}
	
//	@PutMapping("/review")
//	public ResponseEntity<String> update(@RequestBody Review review) {
//		reviewService.modifyReview(review);
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
	@PutMapping("/review")
	public ResponseEntity<List<Review>> update(@RequestBody Review review) {
		reviewService.modifyReview(review);
		return new ResponseEntity<List<Review>>(reviewService.getReviewList(review.getVideoId()), HttpStatus.OK);
	}
}
