package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	// 회원가입 폼 생각해서 추가
	private String userId;
	private String nickname;
	private String password;
	private String name;
	private int age;
	private int grade;
	private String areaName;
	private int classNum;
	private int scoreUpper;
	private int scoreLower;
	private int scoreBelly;
	private int scoreBody;
}
