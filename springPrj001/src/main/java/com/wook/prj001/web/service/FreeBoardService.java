package com.wook.prj001.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.wook.prj001.web.entity.FreeBoard;

public interface FreeBoardService {
	
	List<FreeBoard> getSelect() throws SQLException;

	List<FreeBoard> setList(Map messageBody) throws SQLException;
}
