package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Score;
import com.ssafy.ssafit.service.ScoreService;

@RestController
@RequestMapping("/api")
public class ScoreController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ScoreService scoreService;
	
	@GetMapping("/score/all")
	public ResponseEntity<List<Score>> score() {
		return new ResponseEntity<List<Score>>(scoreService.getScoreList(), HttpStatus.OK);
	}
	
	@GetMapping("/score/area/{areaName}")
	public ResponseEntity<List<Score>> scoreByArea(@PathVariable String areaName) {
		return new ResponseEntity<List<Score>>(scoreService.getScoreListByArea(areaName), HttpStatus.OK);
	}
	
	@GetMapping("/score/rank/{no}")
	public ResponseEntity<Score> scoreByRank(@PathVariable int no) {
		return new ResponseEntity<Score>(scoreService.getScoreByRank(no), HttpStatus.OK);
	}
	
}
