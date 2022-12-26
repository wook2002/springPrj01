package com.wook.prj01.web.board.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wook.prj001.web.entity.FreeBoard;
import com.wook.prj01.web.board.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

//	https://mysterlee.tistory.com/49 (@configuration으로 하는 방법)
		
	@Autowired
	private BoardDao boardDao;
	

	
	@Override
	public List<FreeBoard> getList(int id) {		
		return boardDao.getList(id);
	}

}
