package com.wook.prj01.web.board.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wook.prj01.web.board.dto.Board;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

//	https://mysterlee.tistory.com/49 (@configuration으로 하는 방법)

	
//	private SqlSession sqlSession;
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Board> getList(int id) {	
		
		List<Board> list = null;
		System.out.println("야야야");
		
//		list = session.selectList("boardDB.selectList");
		
		list = session.selectList("boardDB.testSelect");
		
		
		System.out.println("왜왜왜" + list);
		
		return list ;
	}

}
