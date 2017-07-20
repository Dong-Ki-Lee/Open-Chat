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

import work.model.dto.BoardsInfo;
import work.model.dto.MembersInfo;
import work.model.dto.NoticeBoards;
import work.model.dto.PostInfo;
import work.model.dto.Posts;


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
	
	/** 게시판 테이블 조회 */
	public ArrayList<NoticeBoards> selectBoardList() {
		ArrayList<NoticeBoards> list = new ArrayList<NoticeBoards>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from notice_boards_tb order by board_no";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int boardNo = 0;
			String boardTitle = null;
			String boardTag = null;
			
			while(rs.next()) {
				boardNo = rs.getInt("board_no");
				boardTitle = rs.getString("board_title");
				boardTag = rs.getString("board_Tag");
				
				NoticeBoards dto = null;
				dto = new NoticeBoards(boardNo, boardTitle, boardTag);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(게시판 테이블 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	/** 게시판당 게시글 수 조회 */
	public ArrayList<Integer> selectPostsCnt() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(post_no) from posts_tb group by board_no order by board_no";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(rs.getInt("count(post_no)"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(게시글 수 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	/** 게시판 당 구독 수 조회 */
	public ArrayList<Integer> selectSubscribeCnt() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(member_no) from members_subscribe_tb group by notice_board_no order by notice_board_no";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(rs.getInt("count(member_no)"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(게시글 수 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	/** 게시글 정보 조회 */
	public ArrayList<PostInfo> selectPostsList() {
		ArrayList<PostInfo> list = new ArrayList<PostInfo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from posts_tb order by post_no";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int memberNo = 0;
			int boardNo = 0;
			int postNo = 0;
			String postTitle = null;
			String postContent = null;
			String createTime = null;
			int postViews = 0;
			
			PostInfo postInfo = null;
			
			while(rs.next()) {
				memberNo = rs.getInt("member_no");
				boardNo = rs.getInt("board_no");
				postNo = rs.getInt("post_no");
				postTitle = rs.getString("post_title");
				postContent = rs.getString("post_content");
				createTime = rs.getString("create_time");
				postViews = rs.getInt("post_views");
				
				postInfo = new PostInfo(memberNo, boardNo, postNo, postTitle, postContent, createTime, postViews);
				list.add(postInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error (전체 게시글 정보 조회) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	/** 게시글 당 신고수 조회 */
	public ArrayList<Integer> selectDisPostsCnt() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		StringBuilder sql = new StringBuilder();
	    sql.append("select count(member_no) ");
	    sql.append("from (select * from posts_preference_tb where recommend = 0) ");
	    sql.append("group by board_no, post_no");
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				list.add(rs.getInt("count(member_no)"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(신고글 수 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
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
