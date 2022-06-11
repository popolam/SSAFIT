package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	// 리뷰에는 좋아요 기능, 답글 기능 고민
	private int reviewNo;
	private String content;
	private String datetime;
//	private int viewCnt;
	private String videoId;
	private String userId;
}
