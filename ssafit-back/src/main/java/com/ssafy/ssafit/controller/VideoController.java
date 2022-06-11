package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Search;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.service.SearchService;
import com.ssafy.ssafit.service.UserService;
import com.ssafy.ssafit.service.VideoService;

@RestController
@RequestMapping("/api")
public class VideoController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/video")
	public ResponseEntity<List<Video>> list() {
		return new ResponseEntity<List<Video>>(videoService.getVideoList(), HttpStatus.OK);
	}
	
//	@GetMapping("/video/{tag}")
//	public ResponseEntity<List<Video>> listByTag(@PathVariable String tag) {
//		Search search = searchService.getSearchByKeyword(tag);
//		if(search == null) {
//			searchService.writeSearch(tag);
//		} else {
//			searchService.updateCnt(search);
//		}
//		return new ResponseEntity<List<Video>>(videoService.getVideoListByTag(tag), HttpStatus.OK);
//	}
	@GetMapping("/video/{tag}")
	public ResponseEntity<List<HashMap<String, Object>>> listByTag(@PathVariable String tag) {
		Search search = searchService.getSearchByKeyword(tag);
		if(search == null) {
			searchService.writeSearch(tag);
		} else {
			searchService.updateCnt(search);
		}
		
		return new ResponseEntity<List<HashMap<String, Object>>>(videoService.getVideoListByTag(tag), HttpStatus.OK);
	}
	@PostMapping("/video")
	public ResponseEntity<List<HashMap<String, Object>>> listByTag(@RequestBody HashMap<String, String> param) {
		Search search = searchService.getSearchByKeyword(param.get("searchText"));
		if(search == null) {
			searchService.writeSearch(param.get("searchText"));
		} else {
			searchService.updateCnt(search);
		}
		
		return new ResponseEntity<List<HashMap<String, Object>>>(videoService.getVideoListWithStatusByTag(param), HttpStatus.OK);
	}
	
	@GetMapping("/video/keyword")
	public ResponseEntity<List<Video>> listByKeyword(@RequestParam(defaultValue = "") String tag, @RequestParam(defaultValue = "-1") String start, @RequestParam(defaultValue = "-1") String cnt) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tag", tag);
		params.put("start", Integer.parseInt(start));
		params.put("cnt", Integer.parseInt(cnt));
		
		Search search = searchService.getSearchByKeyword(tag);
		if(search == null) {
			searchService.writeSearch(tag);
		} else {
			searchService.updateCnt(search);
		}
		return new ResponseEntity<List<Video>>(videoService.getVideoListByKeyword(params), HttpStatus.OK);
	}
	
//	@GetMapping("/video/detail")
//	public ResponseEntity<Video> one(@RequestParam String videoId, @RequestParam(required = false, defaultValue = "none") String userId) {
//		Video video = videoService.getVideoOne(videoId);
//		User user = userService.myPage(userId);
//		System.out.println(user);
//		
//		if(userId.equals("none")) {
//			
//		} else if(video.getTagName().equals("상체")) {
//			user.setScoreUpper(user.getScoreUpper() + 1);
//			userService.modifyUser(user);
//		} else if(video.getTagName().equals("하체")) {
//			user.setScoreLower(user.getScoreLower() + 1);
//			userService.modifyUser(user);
//		} else if(video.getTagName().equals("복부")) {
//			user.setScoreBelly(user.getScoreBelly() + 1);
//			userService.modifyUser(user);
//		} else {
//			user.setScoreBody(user.getScoreBody() + 1);
//			userService.modifyUser(user);
//		}
//		return new ResponseEntity<Video>(video, HttpStatus.OK);
//	}
	@PostMapping("/video/detail")
	public ResponseEntity<HashMap<String, Object>> one(@RequestBody HashMap<String, String> param) {
		HashMap<String, Object> video = videoService.getVideoOneWithStatus(param);
		System.out.println("- [video] : ");
		System.out.println(video.toString());
		
		User user = userService.myPage(param.get("userId"));
		System.out.println("- [USER] : ");
		System.out.println(user.toString());
		
		if(param.get("userId").equals("")) {
		} else if(video.get("tagName").equals("상체")) {
			user.setScoreUpper(user.getScoreUpper() + 1);
			userService.modifyUser(user);
		} else if(video.get("tagName").equals("하체")) {
			user.setScoreLower(user.getScoreLower() + 1);
			userService.modifyUser(user);
		} else if(video.get("tagName").equals("복부")) {
			user.setScoreBelly(user.getScoreBelly() + 1);
			userService.modifyUser(user);
		} else {
			user.setScoreBody(user.getScoreBody() + 1);
			userService.modifyUser(user);
		}
		
		return new ResponseEntity<HashMap<String, Object>>(video, HttpStatus.OK);
	}
	
	@GetMapping("mark/{userId}")
	public ResponseEntity<List<Video>> markList(@PathVariable String userId) {
		return new ResponseEntity<List<Video>>(videoService.getMarkListByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("ismark")
	public ResponseEntity<Integer> isMark(@RequestParam String userId, @RequestParam String videoId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("videoId", videoId);
		return new ResponseEntity<Integer>(videoService.getMark(params), HttpStatus.OK);
	}
	
	@PostMapping("mark")
	public ResponseEntity<String> markWrite(@RequestBody HashMap<String, String> params) {
		videoService.writeMark(params);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@DeleteMapping("mark")
	public ResponseEntity<String> markDelete(@RequestBody HashMap<String, String> params) {
		if(videoService.removeMark(params)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/follow/{userId}")
	public ResponseEntity<List<Video>> followList(@PathVariable String userId) {
		return new ResponseEntity<List<Video>>(videoService.getFollowListByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("isfollow")
	public ResponseEntity<Integer> isFollow(@RequestParam String userId, @RequestParam String channelId) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("channelId", channelId);
		return new ResponseEntity<Integer>(videoService.getFollow(params), HttpStatus.OK);
	}
	
	@PostMapping("follow")
	public ResponseEntity<String> followWrite(@RequestBody HashMap<String, String> params) {
		videoService.writeFollow(params);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@DeleteMapping("follow")
	public ResponseEntity<String> followDelete(@RequestBody HashMap<String, String> params) {
		if(videoService.removeFollow(params)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
