package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
	private int no;
	private String userId;
	private String areaName;
	private int classNum;
	private int scoreUpper;
	private int scoreLower;
	private int scoreBelly;
	private int scoreBody;
	private int sum;
}
