package com.wook.prj01.web.board.service;

import java.util.List;

import com.wook.prj01.web.board.dto.Board;

public interface BoardService {

	List<Board> getList(int id);

	List<Board> getListPage(int currentBar, int sizeList, String sortBy, String sort);

}