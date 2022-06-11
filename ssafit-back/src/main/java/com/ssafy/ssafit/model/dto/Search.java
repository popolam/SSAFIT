package com.ssafy.ssafit.model.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Search {
	private int no;
	private String keyword;
	private int cnt;
}
