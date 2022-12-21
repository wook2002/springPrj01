package com.wook.prj001.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wook.prj001.web.entity.FreeBoard;
import com.wook.prj001.web.service.FreeBoardService;

@Service
public class JDBCFreeBoard implements FreeBoardService {

	// DB설정
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

//	테스트용
	@Override
	public List<FreeBoard> setList(Map messageBody) throws SQLException {
		System.out.println("열기" + messageBody.get("name"));

		String name = String.valueOf(messageBody.get("name"));
		String comment = String.valueOf(messageBody.get("comment"));

		String sql = "INSERT INTO FREEBOARD(FREENUM, FREENAME, FREECOMMENT,FREEDATE) VALUES(0, ?, ?, SYSDATE)";
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		Connection con = dataSource.getConnection();

		PreparedStatement psmt = null;
		psmt = con.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setString(2, comment);
		int result = psmt.executeUpdate();

		psmt.close();
		con.close();

		System.out.println("jdbc테스트1" + result);
		return list;
	}

	@Override
	public List<FreeBoard> getList() throws SQLException {

		// 나중에 분리 시킬거
		String sql = "SELECT no, title, content, writer, regdata, recommend, lookup FROM FreeBoard ORDER BY no";
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {

			FreeBoard fb = new FreeBoard();

			fb.setNo(rs.getInt("no"));
			fb.setTitle(rs.getString("title"));
			fb.setContent(rs.getString("content"));
			fb.setWriter(rs.getString("writer"));
			fb.setFreeDate(rs.getDate("regdata"));
			fb.setRecommend(rs.getInt("recommend"));
			fb.setLookup(rs.getInt("lookup"));
			list.add(fb);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	@Override
	public List<FreeBoard> getDetail(int id) throws SQLException {

		String sql = "SELECT no, title, content, writer, regdata, recommend, lookup FROM FreeBoard WHERE NO = " + id;

		List<FreeBoard> list = new ArrayList<FreeBoard>();
		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {

			FreeBoard fb = new FreeBoard();

			fb.setNo(rs.getInt("no"));
			fb.setTitle(rs.getString("title"));
			fb.setContent(rs.getString("content"));
			fb.setWriter(rs.getString("writer"));
			fb.setFreeDate(rs.getDate("regdata"));
			fb.setRecommend(rs.getInt("recommend"));
			fb.setLookup(rs.getInt("lookup"));
			list.add(fb);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	@Override
	public int deleteList(int id) throws SQLException {

		String sql = "DELETE FROM FREEBOARD WHERE no = ?";
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, id);

		int result = pstmt.executeUpdate();

		return result;
	}

	@Override
	public int insertList(HashMap<String, String> param) throws SQLException {
		
		//INSERT INTO FREEBOARD(NO,TITLE,CONTENT,WRITER,regdata) VALUES(FREEEMP_SEQ.NEXTVAL,'NAME3', 'COMMET3','writer3', sysdate)
//		writer, title,content
		String title = param.get("title");
		String content = param.get("content");
		String writer = param.get("writer");
	
		 
		String sql = "INSERT INTO FREEBOARD(NO,TITLE,CONTENT,WRITER,regdata) VALUES(FREEEMP_SEQ.NEXTVAL,? ,? ,? , sysdate)";
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);

		int result = pstmt.executeUpdate();
		return result;
	}

}
