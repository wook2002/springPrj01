package com.wook.prj01.web.board.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	//../{id} ~ @PathVariable("id") int id
	@RequestMapping("readListAll")
	public List<Board> getListAll(){return service.getListAll();}
	

//	@RequestParam(value="currentBar", defaultValue="1") int currentBar,
//	@RequestParam(value="sortBy", defaultValue="no") String sortBy,
//	@RequestParam(value="sort", defaultValue="desc") String sort
	// post/readListPage??page=0 & size=2 & sort=no,desc
	@RequestMapping("readListPage/{id}")
	public List<Board> getListPage(
			@PathVariable("id") int id,
			@RequestBody Map<String, Object> map
			) throws SQLException {
		int category_no = id;
		map.put("category_no", category_no);
		return service.getListPage(map);
	}
	
	
	@RequestMapping("detail")
	public List<Board> getDetail() throws SQLException {
		int category_no = 1;
		int post_no = 1;
		return service.getDetail(category_no, post_no);
	}
}
