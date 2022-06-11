package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	private int todoNo;
	private String userId;
	private String todoContent;
	private int todoDone;
	private String todoDate;
}
