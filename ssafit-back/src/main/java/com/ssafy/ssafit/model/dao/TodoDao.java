package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Todo;

public interface TodoDao {
	// 해당 유저의 해당 날짜의 투두리스트 조회
	List<Todo> selectTodoList(HashMap<String, String> params);
	// 투두리스트  추가
	void insertTodo(Todo todo);
	// 투두리스트 삭제
	int deleteTodo(int todoNo);
}
