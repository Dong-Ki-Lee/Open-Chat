package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Comments;
import work.model.dto.Members;
import work.model.dto.NoticeBoards;
import work.model.dto.Posts;
import work.model.dto.PostsPreference;

public class NoticesDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	public Connection getConnection() {
		return factory.getConnection();
	}
	
	/** 조회 */
	// 게시글 조회 (정렬 x)
	public ArrayList<Posts> selectPosts(int boardNo) {
		ArrayList<Posts> list = new ArrayList<Posts>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Posts posts = null;
		int memberNo = 0;
		String memberEmail = null;
		String memberNickname = null;
		String memberPw = null;
		int postNo = 0;
		String postTitle = null;
		String postContent = null;
		String createTime = null;
		int postViews = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
	    sql.append("from (select * from posts_tb a, members_tb b where a.member_no = b.member_no)");
		sql.append("where board_no = ?");
	    try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, boardNo);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				memberNo = rs.getInt("member_no");
				memberEmail = rs.getString("member_email");
				memberNickname = rs.getString("member_nickname");
				memberPw = rs.getString("member_pw");
				postNo = rs.getInt("postNo");
				postTitle = rs.getString("post_title");
				postContent = rs.getString("post_content");
				createTime = rs.getString("create_time");
				postViews = rs.getInt("postViews");
				
				posts = new Posts(memberNo, memberEmail, memberNickname, memberPw, boardNo, postNo, postTitle, postContent, createTime, postViews);
				list.add(posts);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (게시물 조회) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}

	public ArrayList<Comments> selectComments(int boardNo, int postNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Comments comments = null;
		int memberNo = 0;
		String memberEmail = null;
		String memberNickname = null;
		String memberPw = null;
		String content = null;
		String createTime = null;
		ArrayList<Comments> list = new ArrayList<Comments>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
	    sql.append("from (select * from comments_tb a, members_tb b where a.member_no=b.member_no)");
		sql.append("where board_no=? and post_no=?");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, boardNo);
			stmt.setInt(2, postNo);
			rs = stmt.executeQuery();
			while(rs.next()) {				
				memberNo = rs.getInt("member_no");
				memberEmail = rs.getString("member_email");
				memberNickname = rs.getString("member_nickname");
				memberPw = rs.getString("member_pw");
				content = rs.getString("content");
				createTime = rs.getString("create_time");
				
				
				comments = new Comments(memberNo, memberEmail, memberNickname, memberPw, boardNo, postNo, content, createTime);
				list.add(comments);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(댓글 조회) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}

	public String selectNickname(int memberNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String memberNickname = null;
		String sql = "select member_nickname from members_tb where member_no=?";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			while(rs.next()) {				
				memberNickname = rs.getString("member_nickname");
				return memberNickname;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(회원번호로 회원닉네임조회) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}


	
	
	
}
