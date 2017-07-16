package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticesDao {
	private FactoryDao factory = FactoryDao.getInstance();
	public Connection getConnection() {
		return factory.getConnection();
	}
	
	// 게시판 등록 
	public int createNotices() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into notice_boards_tb values(?, ?, ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, x);
			stmt.setString(2, x);
			stmt.setString(3, x);
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error (게시판 등록): " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		
		return 0;
	}
	
	// 게시물 등록 
	public int createPosts() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into posts_tb values(?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, x);
			stmt.setInt(2, x);
			stmt.setInt(3, x);
			stmt.setString(4, x);
			stmt.setString(5, x);
			stmt.setString(6, x);
			stmt.setInt(7, x);
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error (게시글 등록) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// 댓글 등록
	public int createComments() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into comments_tb values(?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, x);
			stmt.setInt(2, x);
			stmt.setInt(3, x);
			stmt.setString(4, x);
			stmt.setString(5, x);
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error (댓글 등록) : " + e.getMessage());
			// TODO: handle exception
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// 게시물 조회		+ 정렬
	// 댓글 조회	 + 정렬
	// 등록 게시글 수
	// 댓글 수 조회
	
	// 게시판 생성
//	-- 마일리지 차감
//	-- 키워드 등록
//	게시글
//	-- 추천수 최신수 정렬 가능
//	반대수 이상 되면 글 숨김 -- 게시글 / 댓글
}
