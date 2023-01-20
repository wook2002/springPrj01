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

//	private SqlSession sqlSession;
	@Autowired
	private SqlSessionTemplate session;
	
	// 테스트
	@Override
	public List<Board> getListAll() {	
		List<Board> list = session.selectList("boardMapper.selectList");
		return list ;
	}
	
	// 페이지
	@Override
	public int getListcount(int id) {
		int countList = session.selectOne("boardMapper.countList", id);
		return countList;
	}
	
	@Override
	public Map<String, Object> getPageItem(Map<String,Object> map) {
//		{"sizeList":"10","sortBy":"post_no","category_no":1,"sort":"DESC","currentBar":"1","sizeBar":"5"}
		int currentBar = Integer.parseInt(map.get("currentBar").toString());
		int sizeList = Integer.parseInt(map.get("sizeList").toString());
		int sizeBar = Integer.parseInt(map.get("sizeBar").toString());
		int countList = Integer.parseInt(map.get("countList").toString());
		
		// 페이징
		Page page =  new Page(currentBar, countList, sizeList, sizeBar);

		// Page결과 -> map
		int beginList = page.getBeginList();
		int endList = page.getEndList();
		int maxiumBar = page.getMaxiumBar();
		int beginBar = page.getBeginBar();
		int endBar = page.getEndBar();
		map.put("beginList", beginList);
		map.put("endList", endList);
		map.put("maxiumBar", maxiumBar);
		map.put("beginBar", beginBar);
		map.put("endBar", endBar);
		return map;
	}

	@Override
	public List<Board> getListPage(Map<String,Object> map) {
		List<Board> list = session.selectList("boardMapper.selectListPage", map);
		return list;
	}

	@Override
	public List<Board> getDetail(Map<String, Object> map) {
		List<Board> list = session.selectList("boardMapper.selectDetail", map);
		return list;
	}

	@Override
	public int createBoard(HashMap<String, String> map) {
		int result = session.insert("boardMapper.insertList", map);
		return result;
	}

	@Override
	public int deleteBoard(Map<String, Object> map) {
		int result = session.insert("boardMapper.deleteBoard", map);
		return result;
	}

	@Override
	public int updateSet(HashMap<String, String> map) {
		int result = session.insert("boardMapper.updateSet", map);
		return result;
	}
}
