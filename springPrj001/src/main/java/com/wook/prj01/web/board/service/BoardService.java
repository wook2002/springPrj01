package com.wook.prj01.web.board.service;

import java.util.List;
import java.util.Map;

import com.wook.prj01.web.board.dto.Board;

public interface BoardService {

	List<Board> getListAll();

	List<Board> getListPage(Map<String,Object> map);

	List<Board> getDetail(int category_no, int post_no);
	
	Map<String, Object> getPageItem(Map<String,Object> map);
	
	int getListcount(int id);

}
