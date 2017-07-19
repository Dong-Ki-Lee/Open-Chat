package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Comments;
import work.model.dto.NoticeBoards;
import work.model.dto.Posts;
import work.model.dto.PostsPreference;

public class NoticesDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	public Connection getConnection() {
		return factory.getConnection();
	}
	/** ��� */
	// �Խ��� ��� 
	public int insertNotices(NoticeBoards noticeBoards) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into notice_boards_tb values(?, ?, ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeBoards.getBoardNo());
			stmt.setString(2, noticeBoards.getBoardTitle());
			stmt.setString(3, noticeBoards.getBoardTag());
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error (�Խ��� ���): " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		
		return 0;
	}
	
	// �Խñ� ��� 
	public int insertPosts(Posts posts) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into posts_tb values(?, ?, ?, ?, ?, ?, ?)";
		try {
			// 1000 ������ �������� / 1000 �ʰ� �� ��� �Ϲ� �Խñ�
			
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, posts.getMemberNo());
			stmt.setInt(2, posts.getBoardNo());
			stmt.setInt(3, posts.getPostNo());
			stmt.setString(4, posts.getPostTitle());
			stmt.setString(5, posts.getPostContent());
			stmt.setString(6, posts.getCreateTime());
			stmt.setInt(7, posts.getPostViews());
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error (�Խñ� ���) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// ��� ���
	public int insertComments(Comments comments) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String sql = "insert into comments_tb values(?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comments.getMemberNo());
			stmt.setInt(2, comments.getBoardNo());
			stmt.setInt(3, comments.getPostNo());
			stmt.setInt(4, comments.getCommentNo());
			stmt.setString(5, comments.getContent());
			stmt.setString(6, comments.getCreateTime());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error (��� ���) : " + e.getMessage());
			// TODO: handle exception
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	/** ���� */
	// �Խñ� ����
	public int updatePosts(Posts posts, String member_nickname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update posts_tb set ");
		sql.append("post_title = ?, post_content = ?");
		sql.append("where board_no = ? and member_no = (select member_no from members_tb where member_nickname = ?)");
	
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, posts.getPostTitle());
			stmt.setString(2, posts.getPostContent());
			stmt.setInt(3, posts.getBoardNo());
			stmt.setString(4, member_nickname);
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error (�Խñ� ����) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// ��� ����
	public int updateComments(Comments comments, String member_nickname) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update comments_tb set ");
		sql.append("content = ?");
		sql.append("where comment_no = ? and member_no = (select member_no from members_tb where member_nickname = ?)");
	
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, comments.getContent());
			stmt.setInt(2, comments.getCommentNo());
			stmt.setString(3, member_nickname);
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error (��� ����) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	/** ���� */
	// �Խñ� ����
	public int deletePosts(Posts posts, String member_nickname) {
		Connection conn = null;
		PreparedStatement stmt = null;
				
		String sql = "delete from posts_tb where posts_no = ? and member_no = (select member_no from members_tb where member_nickname = ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, posts.getPostNo());
			stmt.setString(2, member_nickname);
			
			return stmt.executeUpdate();
						
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (�Խñ� ����) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// ��� ����
	public int deleteComments(Comments comments, String member_nickname) {
		Connection conn = null;
		PreparedStatement stmt = null;
				
		String sql = "delete from comments_tb where comment_no = ? and member_no = (select member_no from members_tb where member_nickname = ?)";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comments.getCommentNo());
			stmt.setString(2, member_nickname);
			
			return stmt.executeUpdate();
						
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (��� ����) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	/** ��ȸ */
	// �Խñ� ��ȸ (���� x)
	public ArrayList<Posts> selectPosts(int boardNo) {
		ArrayList<Posts> list = new ArrayList<Posts>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int memberNo = 0;
		int postNo = 0;
		String postTitle = null;
		String postContent = null;
		String createTime = null;
		int postViews = 0;
		Posts posts = null;
		String sql = "select * from posts_tb where board_no = ?";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				memberNo = rs.getInt("member_no");
				boardNo = rs.getInt("board_no");
				postNo = rs.getInt("post_no");
				postTitle = rs.getString("post_title");
				postContent = rs.getString("post_content");
				createTime = rs.getString("create_time");
				postViews = rs.getInt("post_views");
				
				posts = new Posts(memberNo, boardNo, postNo, postTitle, postContent, createTime, postViews);
				list.add(posts);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (�Խñ� ��ȸ - ����x) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	// �Խù� ��ȸ // �ۼ��� �ϴ� ����
	// temp => ���� �Ӽ��� , sort => ���Ĺ��
	public ArrayList<Posts> selectPosts(String temp, String sort) {
		ArrayList<Posts> list = new ArrayList<Posts>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int postNo = 0;
		String postTitle = null;
		String postContent = null;
		String createTime = null;
		int postViews = 0;
		Posts posts = null;
		String sql = "select post_no, post_title, post_content, create_time,  post_views from posts_tb order by ? ?";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, temp);
			stmt.setString(2, sort);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				postNo = rs.getInt("post_no");
				postTitle = rs.getString("post_title");
				postContent = rs.getString("post_content");
				createTime = rs.getString("create_time");
				postViews = rs.getInt("post_views");
				
				posts = new Posts(postNo, postTitle, postContent, createTime, postViews);
				list.add(posts);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (�Խù� ��ȸ) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	// ��� ��ȸ // �ۼ��� ��ȸ
	// temp ���� �Ӽ� , sort ���� ���
	public ArrayList<Comments> selectComments(String temp, String sort) {
		ArrayList<Comments> list = new ArrayList<Comments>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int commentNo = 0;
		String content = null;
		String createTime = null;
		Comments comments = null;
		String sql = "select comment_no, content, create_time from comments_tb order by ? ?";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, temp);
			stmt.setString(2, sort);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				commentNo = rs.getInt("comment_no");
				content = rs.getString("content");
				createTime = rs.getString("create_time");
				
				comments = new Comments(commentNo, content, createTime);
				list.add(comments);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (��� ��ȸ) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	// ��õ�� ��ȸ
	public ArrayList<PostsPreference> selectRecommend(int boardNo) {
		ArrayList<PostsPreference> list = new ArrayList<PostsPreference>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int memberNo = 0;
		int postNo = 0;
		int recommend = 0;
		PostsPreference postsPreference = null;
		String sql = "select * from posts_preference_tb where board_no = ?";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				memberNo = rs.getInt("member_no");
				postNo = rs.getInt("post_no");
				recommend = rs.getInt("recommend");
				
				postsPreference = new PostsPreference(memberNo, boardNo, postNo, recommend);
				list.add(postsPreference);				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error (��õ�� ��ȸ) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	
}
