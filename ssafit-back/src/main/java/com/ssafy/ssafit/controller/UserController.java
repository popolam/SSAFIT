package com.ssafy.ssafit.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.service.FigureService;
import com.ssafy.ssafit.service.UserService;
import com.ssafy.ssafit.util.JWTUtil;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FigureService figureService;
	
	@Autowired
	private ServletContext servletContext;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		System.out.println("user: " + user);
		HttpStatus status = null;
		HashMap<String, Object> result = new HashMap<>();
		try {
			// user 정보를 이용해서 데이터베이스 확인
			// 존재하면 토큰 생성해서 결과에 넣어서 반환
			if(userService.login(user.getUserId()) == null || user.getUserId().length() == 0) {
				result.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
				System.out.println("아이디가 틀려");
			}
			
			// 비밀번호도 같다면
			if(userService.login(user.getUserId()).getPassword().equals(user.getPassword())) {
				result.put("userId", user.getUserId());
				result.put("grade", user.getGrade());
				result.put("auth-token", jwtUtil.createToken("userId", user.getUserId()));
				result.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} catch(Exception e) {
			e.printStackTrace();
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println("비밀번호가 틀려");
		}
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody User user) {
		userService.join(user);
		figureService.writeFigure(user.getUserId());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@GetMapping("/myPage/{userId}")
	public ResponseEntity<User> myPage(@PathVariable String userId) {
		return new ResponseEntity<User>(userService.myPage(userId), HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Map<String, Object>> validate(@RequestBody Map<String, String> param) throws Exception {
		// 유효 시간이 있다면 유효성 검사를 거친 이후 이므로 토큰과 해당하는 유저 아이디를 새로 발급해준다.
		// 유저 아이디 검출
		Map<String, Object> result = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(param.get("auth-token").split("\\.")[1])), Map.class);
		// 토큰 재발급
		result.put("auth-token", jwtUtil.createToken("userId", result.get("userId").toString()));
		System.out.println(result.toString());
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
