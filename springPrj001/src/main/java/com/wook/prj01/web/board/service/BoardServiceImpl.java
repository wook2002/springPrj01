package com.wook.prj01.web.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.wook.prj01.web.board.dto.Board;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

//	https://mysterlee.tistory.com/49 (@configuration으로 하는 방법)

	SqlSession session;
	
	@Override
	public List<Board> getList(int id) {	
		
		System.out.println("야야야");
		List<Board> list;
		list = session.selectList(".selectList");
		
		System.out.println("왜왜왜");
		
		return list ;
	}

}
