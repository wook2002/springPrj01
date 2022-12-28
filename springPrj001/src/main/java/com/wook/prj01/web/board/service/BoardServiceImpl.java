package com.wook.prj01.web.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wook.prj01.web.board.dto.Board;
import com.wook.prj01.web.board.dto.Page;

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
		
		list = session.selectList("boardMapper.selectList");
		
		System.out.println("왜왜왜 : " + list);
		
		return list ;
	}

	@Override
	public List<Board> getListPage(int currentBar, int sizeList, String sortBy, String sort) {

		
		int countList = 35;
		int sizeBar = 5;
		currentBar = 1;
		sizeList = 10;
		sortBy = "post_no";
		sort = "DESC";
		
		//
		int listcount = session.selectOne("boardMapper.countList");
		System.out.println("listcount : " + listcount);
		
		//
		Page page = new Page(currentBar, countList, sizeList, sizeBar);

		//
		int beginList = page.getBeginList();
		int endList = page.getEndList();
		sortBy = page.getSortBy();
		sort = page.getSort();
		
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("beginList", beginList);
		map.put("endList", endList);
		map.put("sortBy", sortBy);
		map.put("sort", sort);
		System.out.println("map" + map);
		
		List<Board> list = null;
		list = session.selectList("boardMapper.selectListPage", map);
		
		System.out.println(page.toString());
		System.out.println("list : " + list);
		
		return list;
	}

}
