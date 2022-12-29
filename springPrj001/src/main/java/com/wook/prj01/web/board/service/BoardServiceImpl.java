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
	public List<Board> getListAll() {	
		List<Board> list = session.selectList("boardMapper.selectList");
		return list ;
	}

	@Override
	public List<Board> getListPage(Map<String,Object> map) {
		// Page에 넣을 값
		int countList = session.selectOne("boardMapper.countList");
		int currentBar = Integer.parseInt(map.get("currentBar").toString());
		int sizeList = Integer.parseInt(map.get("sizeList").toString());
		int sizeBar = Integer.parseInt(map.get("sizeBar").toString());
		Page page =  new Page(currentBar, countList, sizeList, sizeBar);

		// Page 결과 값을 -> map에
		int beginList = page.getBeginList();
		int endList = page.getEndList();
		map.put("beginList", beginList);
		map.put("endList", endList);
		
		// map을 -> mapper에
		List<Board> list = null;
		list = session.selectList("boardMapper.selectListPage", map);
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
