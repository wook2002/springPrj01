package com.wook.prj01.web.board.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wook.prj001.web.entity.FreeBoard;

@Repository
public class BoardDaoImpl implements BoardDao {

	// DB설정
	@Autowired
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<FreeBoard> getList(int id) {
		
		List<FreeBoard> list = null;
		
		System.out.println("DAOs까지 잘옴" + id);
		
		return list;
	}

}
