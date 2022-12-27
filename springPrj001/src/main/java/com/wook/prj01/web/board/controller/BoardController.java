package com.wook.prj01.web.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wook.prj01.web.board.dto.Board;
import com.wook.prj01.web.board.service.BoardService;

@CrossOrigin(origins = "*")
@RestController("boardController")
@RequestMapping("/post/")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("readList/{id}")
	public List<Board> getList(@PathVariable("id") int id) throws SQLException {
		System.out.println(id);
		System.out.println("됨2?");
		return service.getList(id);
	}
}
