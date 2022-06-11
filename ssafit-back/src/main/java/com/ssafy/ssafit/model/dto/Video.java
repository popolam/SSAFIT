package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
	// 비디오 조회수 추가 고민
	private String videoId;
	private String title;
	private String channelId;
	private String channelName;
	private String tagName;
	private int viewCnt;
}
