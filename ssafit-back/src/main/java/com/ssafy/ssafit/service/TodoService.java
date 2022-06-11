package com.ssafy.ssafit.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Todo;

public interface TodoService {
	// 해당 유저의 해당 날짜 투두리스트 조회
	List<Todo> getTodoList(HashMap<String, String> params);
	// 해당 유저의 해당 날짜 투두리스트 추가
	void writeTodo(Todo todo);
	// 해당 유저의 해당 날짜 투두리스트 삭제
	boolean removeTodo(int todoNo);
}
