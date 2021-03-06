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
   
   /** 전체 게시글 조회 */
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
       sql.append("from (select a.member_no, a.board_no, a.post_no, a.post_title, a.post_content, a.create_time, a.post_views, b.member_email, b.member_nickname, b.member_pw from posts_tb a, members_tb b where a.member_no = b.member_no) ");
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
            postNo = rs.getInt("post_no");
            postTitle = rs.getString("post_title");
            postContent = rs.getString("post_content");
            createTime = rs.getString("create_time");
            postViews = rs.getInt("post_views");
            
            posts = new Posts(memberNo, memberEmail, memberNickname, memberPw, boardNo, postNo, postTitle, postContent, createTime, postViews);
            list.add(posts);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error (게시물 조회) : " + e.getMessage());
      } finally {
         factory.close(rs, stmt, conn);
      }
      return list;
   }

   /** 해당 게시판, 게시글의 댓글 조회 */
   public ArrayList<Comments> selectComments(int boardNo, int postNo) {
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
      System.out.println(boardNo+", "+postNo);
      StringBuilder sql = new StringBuilder();
      sql.append("select * ");
       sql.append("from (select b.member_no, b.member_email, b.member_nickname, b.member_pw, a.board_no, a.post_no, a.content, a.create_time from comments_tb a, members_tb b where a.member_no=b.member_no) ");
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

   /** 회원 닉네임 조회 */
   public String selectNickname(int memberNo) {
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

   /** 게시글 조회 */
   public Posts selectPosts(int boardNo, int postNo) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      Posts posts = null;
      int memberNo = 0;
      String memberEmail = null;
      String memberNickname = null;
      String memberPw = null;
      String postTitle = null;
      String postContent = null;
      String createTime = null;
      int postViews = 0;
      
      StringBuilder sql = new StringBuilder();
      sql.append("select * ");
       sql.append("from (select a.member_no, a.board_no, a.post_no, a.post_title, a.post_content, a.create_time, a.post_views, b.member_email, b.member_nickname, b.member_pw from posts_tb a, members_tb b where a.member_no = b.member_no) ");
      sql.append("where board_no = ? and post_no = ?");
       try {
         conn = getConnection();
         stmt = conn.prepareStatement(sql.toString());
         stmt.setInt(1, boardNo);
         stmt.setInt(2, postNo);
         rs = stmt.executeQuery();
         while(rs.next()) {
            memberNo = rs.getInt(1);
            memberEmail = rs.getString(8);
            memberNickname = rs.getString(9);
            memberPw = rs.getString(10);
            postTitle = rs.getString(4);
            postContent = rs.getString(5);
            createTime = rs.getString(6);
            postViews = rs.getInt(7);
            posts = new Posts(memberNo, memberEmail, memberNickname, memberPw, boardNo, postNo, postTitle, postContent, createTime, postViews);
            return posts;
         }
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error (게시물 조회) : " + e.getMessage());
      } finally {
         factory.close(rs, stmt, conn);
      }
      return null;
   }

   /** 게시글 생성 */
   public int createPost(int memberNo, int boardNo, String postTitle, String postContent) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql = "insert into posts_tb values(?, ?, ?, ?, ?, ?, ?)";
      try {
         conn = getConnection();
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, memberNo);
         stmt.setInt(2, boardNo);
         stmt.setInt(3, 10);
         stmt.setString(4, postTitle);
         stmt.setString(5, postContent);
         stmt.setString(6, "20170721");
         stmt.setInt(7, 9);
         
         return stmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error (게시물 생성) : " + e.getMessage());
      } finally {
         factory.close(rs, stmt, conn);
      }
      return 0;
   }
   
   /** 게시글 삭제 */
   public int deletePost(int boardNo, int postNo) {
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      String sql = "delete from posts_tb where board_no=? and post_no=?";
	      try {
	         conn = getConnection();
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, boardNo);
	         stmt.setInt(2, postNo);
	         
	         return stmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("Error (게시물 삭제) : " + e.getMessage());
	      } finally {
	         factory.close(rs, stmt, conn);
	      }
	      return 0;
	   }

   /** 게시글 삭제 */
   public int deletePost(int memberNo, int createMemberNo, int boardNo, int postNo) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql = "delete from posts_tb where board_no=? and post_no=?";
      
      try {
         conn = getConnection();
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, boardNo);
         stmt.setInt(2, postNo);
         
         return stmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error (게시물 삭제) : " + e.getMessage());
      } finally {
         factory.close(rs, stmt, conn);
      }
      return 0;
   }

   /** 댓글 생성 */
   public int createComments(int memberNo, int boardNo, int postNo, String content) {
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      String sql = "insert into comments_tb(member_no, board_no, post_no, content) values(?, ?, ?, ?)";
      
      try {
         conn = getConnection();
         stmt = conn.prepareStatement(sql);
         stmt.setInt(1, memberNo);
         stmt.setInt(2, boardNo);
         stmt.setInt(3, postNo);
         stmt.setString(4, content);
         
         return stmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Error (댓글 등록) : " + e.getMessage());
      } finally {
         factory.close(rs, stmt, conn);
      }
      return 0;
   }
   
}