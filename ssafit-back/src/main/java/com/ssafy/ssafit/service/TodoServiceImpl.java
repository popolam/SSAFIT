package com.ssafy.ssafit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.TodoDao;
import com.ssafy.ssafit.model.dto.Todo;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoDao todoDao;

	@Override
	public List<Todo> getTodoList(HashMap<String, String> params) {
		return todoDao.selectTodoList(params);
	}

	@Override
	public void writeTodo(Todo todo) {
		todoDao.insertTodo(todo);
	}

	@Override
	public boolean removeTodo(int todoNo) {
		return todoDao.deleteTodo(todoNo) == 1;
	}
}
