package com.wook.prj01.web.board.dao;

import java.util.List;

import com.wook.prj001.web.entity.FreeBoard;

public interface BoardDao {

	List<FreeBoard> getList(int id);

}
