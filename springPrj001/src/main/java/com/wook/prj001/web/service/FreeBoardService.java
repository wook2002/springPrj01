package com.wook.prj001.web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wook.prj001.web.entity.FreeBoard;

public interface FreeBoardService {
	
//	
	List<FreeBoard> setList(Map messageBody) throws SQLException;
	
	List<FreeBoard> getList() throws SQLException;

	List<FreeBoard> getDetail(int id) throws SQLException;

	int deleteList(int id) throws SQLException;

	int insertList(HashMap<String, String> param) throws SQLException;

}
