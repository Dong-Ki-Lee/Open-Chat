/**
 * 
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * ## DAO Pattern
 * => DataSource(FactoryDao)에게 Connection, close 사용
 * 
 * @author limjinha
 */
public class AdminDao {

	private FactoryDao factory = FactoryDao.getInstance();
	
	public Connection getConnection() {
		return factory.getConnection();
	}
	
	public String selectNoticeList() {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		StringBuilder sql = new StringBuilder();
	    sql.append("select c.board_no, c.board_title, d.count(b.post_no), count(d.member_no) ");
	    sql.append("from ");
	    sql.append("(select a.board_no, a.board_title, count(b.post_no) ");
	    sql.append("from notice_boards_tb a, posts_tb b ");
	    sql.append("where a.board_no = b.board_no ");
	    sql.append("group by board_no) c, members_subscribe_tb d ");
	    sql.append("where c.board_no = d.board_no ");
	    sql.append("group by board_no");
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			int boardNo = rs.getInt(1);
			String boardName = rs.getString(2);
			int countPosts = rs.getInt(3);
			int countSubscribe = rs.getInt(4);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(게시판 목록 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	
	public String selectDisagreeNoticeByDate() {
		return null;
	}
	
	public int insertQnAComments(int memberNo, int postNo, int commentNo, String content, String createTime) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "insert into comments_tb values(?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, memberNo);
			stmt.setInt(2, postNo);
			stmt.setInt(3, commentNo);
			stmt.setString(4, content);
			stmt.setString(5, createTime);
			
			return stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(QnA댓글 등록 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	public int NewMemberCount(String date) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		StringBuilder sql = new StringBuilder();
	    sql.append("select count(*) ");
	    sql.append("from members_info_tb ");
	    sql.append("where join_date = ?");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, date);
			rs = stmt.executeQuery(sql.toString());
			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(신규가입 회원 수 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	


}
