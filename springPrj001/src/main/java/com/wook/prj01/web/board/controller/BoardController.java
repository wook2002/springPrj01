package com.wook.prj01.web.board.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wook.prj01.web.board.dto.Board;
import com.wook.prj01.web.board.service.BoardService;

@CrossOrigin(origins = "*")
@RestController("boardController")
@RequestMapping("/post/")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//테스트
	@RequestMapping("readListAll")
	public List<Board> getListAll(){return service.getListAll();}
	
	//페이지
	@RequestMapping("getListcount/{id}")
	public int getListcount(@PathVariable("id") int id){return service.getListcount(id);}
	
	@RequestMapping("getPageItem/{id}")
	public Map<String, Object> getPageItem(
			@PathVariable("id") int id,
			@RequestBody Map<String, Object> map){
		int countList = service.getListcount(id);
		map.put("countList", countList);
		return service.getPageItem(map);
	}

	@RequestMapping("readListPage/{id}")
	public Map<String, Object> getListPage(
			@PathVariable("id") int id
			, @RequestBody Map<String, Object> map
			) throws SQLException, JsonProcessingException, IllegalArgumentException {
		
		//tempData
//		Map<String, Object> map = new HashMap<String, Object>();
//		String currentBar = "1";
//		String sizeList = "10";
//		String sizeBar = "5";
//		String sortBy = "post_no";
//		String sort = "DESC";
//		map.put("currentBar", currentBar);
//		map.put("sizeList", sizeList);
//		map.put("sizeBar", sizeBar);
//		map.put("sortBy", sortBy);
//		map.put("sort", sort);
//		{"sizeList":"10","sortBy":"post_no","category_no":1,"sort":"DESC","currentBar":"1","sizeBar":"5"}  
		
		int category_no = id;
		map.put("category_no", category_no);
		
		int countList = service.getListcount(category_no);
		map.put("countList", countList);
		
		
		map = service.getPageItem(map);
		List<Board> list = service.getListPage(map);
		map.put("list", list);

		return map;
	}

//	@RequestParam(value="currentBar", defaultValue="1") int currentBar,
//	@RequestParam(value="sortBy", defaultValue="no") String sortBy,
//	@RequestParam(value="sort", defaultValue="desc") String sort
	// post/readListPage??page=0 & size=2 & sort=no,desc
	
	@RequestMapping("detail/{id}")
	public List<Board> getDetail(
			@PathVariable("id") int id,
			@RequestParam(value="no", defaultValue="1") int no) throws SQLException {
		System.out.println("id : " + id);
		System.out.println("no : " + no);
		int category_no = 1;
		int post_no = 1;
		return service.getDetail(category_no, post_no);
	}
	
	
}
