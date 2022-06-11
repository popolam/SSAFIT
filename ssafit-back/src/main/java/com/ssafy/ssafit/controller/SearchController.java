package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Search;
import com.ssafy.ssafit.service.SearchService;

@RestController
@RequestMapping("/api")
public class SearchController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/ranking")
	public ResponseEntity<List<Search>> list() {
		return new ResponseEntity<List<Search>>(searchService.getSearchList(), HttpStatus.OK);
	}
}
