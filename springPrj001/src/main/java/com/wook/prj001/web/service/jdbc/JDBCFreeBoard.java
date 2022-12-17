package com.wook.prj001.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	@Override
	public List<FreeBoard> getSelect() throws SQLException {

		// 나중에 분리 시킬거
		String sql = "SELECT no, title, content, writer, regdata, recommend, lookup FROM FreeBoard";
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
}
