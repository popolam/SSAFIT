package com.ssafy.ssafit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.ssafit.util.JWTUtil;

@Component
public class JWTInterceptor implements HandlerInterceptor {
	private static final String HEADER_AUTH = "auth-token";	
	
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 추가된 부분
	    if (request.getMethod().equals("OPTIONS")) {
	        return true;
	    }
		
		// 사용자가 요청시 헤더에 보내준 토큰 가져오기
		final String token = request.getHeader(HEADER_AUTH);
		System.out.println("token : " + token);
		if(token != null){
	        jwtUtil.valid(token);
//	        Logger.info("토큰 사용 가능 : {}", token);
	        return true;
	    }
	    throw new Exception("유효하지 않은 접근입니다.");
	}
}
