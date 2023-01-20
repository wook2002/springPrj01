package com.wook.prj01.web.board.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			@PathVariable("id") int category_no
			, @RequestBody Map<String, Object> map
			) throws SQLException, JsonProcessingException, IllegalArgumentException {
		int countList;
		List<Board> list;
		
		// category_no, countList
		map.put("category_no", category_no);
		countList= service.getListcount(category_no);
		map.put("countList", countList);
		
		// getPageItem, getList
		map = service.getPageItem(map);
		list = service.getListPage(map);
		map.put("list", list);
		System.out.println(map);
		
		return map;
	}

	@RequestMapping("detail/{id}")
	public List<Board>  getDetail(
			@PathVariable("id") int categoryNo,
			@RequestParam(name="bno", defaultValue="1") String bno) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map.put("category_no", categoryNo);
		map.put("post_no", bno);
		return service.getDetail(map);
	}
	
	@RequestMapping(value = "createBoard/{id}", method = RequestMethod.POST)
	public int createBoard(
			@PathVariable("id") String categoryNo,
			@RequestBody HashMap<String, String> map) throws SQLException {
		map.put("category_no", categoryNo);
		return service.createBoard(map);
	}
	
	@RequestMapping("deleteBoard/{id}")
	public int deleteBoard(
			@PathVariable("id") int categoryNo,
			@RequestParam(name="bno", defaultValue="1") int bno){
		Map<String, Object> map = new HashMap<>();
		map.put("category_no", categoryNo);
		map.put("post_no", bno);
		return service.deleteBoard(map);
	}
	
//	updateGet => getDetail
	@RequestMapping(value = "updateSet", method = RequestMethod.POST)
	public int updateSet(
			@RequestBody HashMap<String, String> map,
			@RequestParam(name="bno", defaultValue="100") String bno) throws SQLException{
		map.put("post_no", bno);
		return service.updateSet(map);
	}
	
}
