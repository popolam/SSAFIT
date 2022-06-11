package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Todo;
import com.ssafy.ssafit.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todo")
	public ResponseEntity<List<Todo>> list(@RequestParam String userId, @RequestParam String todoDate) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("todoDate", todoDate);
		
		return new ResponseEntity<List<Todo>>(todoService.getTodoList(params), HttpStatus.OK);
	}
	
	@PostMapping("/todo")
	public ResponseEntity<String> wrire(@RequestBody Todo todo) {
		todoService.writeTodo(todo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/todo")
	public ResponseEntity<List<Todo>> delete(@RequestBody Map<String, Object> payload) {
		System.out.println(payload);
		todoService.removeTodo(Integer.parseInt(payload.get("todoNo").toString()));
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", payload.get("userId").toString());
		params.put("todoDate", payload.get("todoDate").toString());
		return new ResponseEntity<List<Todo>>(todoService.getTodoList(params), HttpStatus.OK); 
	}
}
