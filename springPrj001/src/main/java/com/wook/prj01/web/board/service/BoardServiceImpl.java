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
	
	// 테스트용
	@Override
	public List<Board> getListAll() {	
		List<Board> list = session.selectList("boardMapper.selectList");
		return list ;
	}
	
	@Override
	public int getListcount(int id) {
		int countList = session.selectOne("boardMapper.countList", id);
		return countList;
	}
	
	@Override
	public Map<String, Object> getPageItem(Map<String,Object> map) {
		// Page에 넣을 값
		int currentBar = Integer.parseInt(map.get("currentBar").toString());
		int sizeList = Integer.parseInt(map.get("sizeList").toString());
		int sizeBar = Integer.parseInt(map.get("sizeBar").toString());
		int countList = Integer.parseInt(map.get("countList").toString());
		Page page =  new Page(currentBar, countList, sizeList, sizeBar);

		// Page결과 -> map
		int beginList = page.getBeginList();
		int endList = page.getEndList();
		int maxiumBar = page.getMaxiumBar();
		map.put("beginList", beginList);
		map.put("endList", endList);
		map.put("maxiumBar", maxiumBar);
		
		
		System.out.println("map2 : "+ map);

		return map;
	}

	@Override
	public List<Board> getListPage(Map<String,Object> map) {
		
		System.out.println("map3 : "+ map);
		// paging
		List<Board> list = session.selectList("boardMapper.selectListPage", map);
		
		System.out.println("list : " + list);
		return list;
	}

	@Override
	public List<Board> getDetail(int category_no, int post_no) {
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("category_no", category_no);
		map.put("post_no", post_no);
		List<Board> list = session.selectList("boardMapper.selectDetail", map);
		return list;
	}

	

}
