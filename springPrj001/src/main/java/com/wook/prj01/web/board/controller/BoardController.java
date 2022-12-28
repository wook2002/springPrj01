package com.wook.prj01.web.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		System.out.println("readList : Controller");
		return service.getList(id);
	}
	
	
	// pageable, page, size, sortBy, desc
	// post/readListPage??page=0&size=2&sort=no,desc
	// post/readListPage??page=0 & size=2 & sort=no,desc
	@RequestMapping("readListPage")
	public List<Board> getListPage(
			@RequestParam(value="currentBar", defaultValue="1") int currentBar,
			@RequestParam(value="sortBy", defaultValue="no") String sortBy,
			@RequestParam(value="sort", defaultValue="desc") String sort
			) throws SQLException {
		
		int sizeList = 10;
		
		

		return service.getListPage(currentBar, sizeList, sortBy, sort );
//		return null;
	}
}
