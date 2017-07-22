package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import work.model.dto.Members;
import work.model.dto.MembersInfo;

public class MembersDao {
	private FactoryDao factory = FactoryDao.getInstance();

	public Connection getConnection() {
		return factory.getConnection();
	}

	/** ȸ�� ���� */
	public int delete(int memberNo) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "delete members_tb where member_no = ? ";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, memberNo);

			return stmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR(ȸ��Ż�� ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}

	/** ȸ�� ���� ��� */
	public int insert(Members dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into members_tb values(?, ?, ?, ?)";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, dto.getMemberNo());
			stmt.setString(2, dto.getMemberEmail());
			stmt.setString(3, dto.getMemberNickname());
			stmt.setString(4, dto.getMemberPw());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(ȸ����� ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}		
	
	/** ȸ������ ���� */
	public int update(Members dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("update members_tb set");
		sql.append("member_no=?, member_email=?, member_nickname=?, member_pw=?");
		sql.append("where member_no=?");

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, dto.getMemberNo());
			stmt.setString(2, dto.getMemberEmail());
			stmt.setString(3, dto.getMemberNickname());
			stmt.setString(4, dto.getMemberPw());

			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(ȸ�� ������ ���� ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}	

	/** �α��� Ȯ�� */
	public int loginCheck(String memberEmail, String memberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select member_no from members_tb where member_email=? and member_pw=?";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberEmail);
			stmt.setString(2, memberPw);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("member_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(�α��ο���) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	

	/** �ش� ȸ�� �г��� ��ȸ  */
	public String selectMyNickname(int memberNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select member_nickname ");
		sql.append("from members_tb ");
		sql.append("where member_no=?");

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, memberNo);			
			rs = stmt.executeQuery();

			while(rs.next()) {

				return rs.getString("member_nickname");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(ȸ�� �г��� ��ȸ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}

	/** �ش� ȸ���� ���ϸ��� ��ȸ  */
	public int selectMileage(int memberNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select mileage ");
		sql.append("from members_info_tb ");
		sql.append("where member_no=?");

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, memberNo);			
			rs = stmt.executeQuery();
			while(rs.next()) {

				return rs.getInt("mileage");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(���ϸ��� ��ȸ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	
	/** �ش� ȸ���� �Խñ� �� ��ȸ */
	public int selectPostCnt(int memberNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(post_no) ");
		sql.append("from posts_no ");
		sql.append("where member_no=?");

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, memberNo);			
			rs = stmt.executeQuery();
			while(rs.next()){
				return rs.getInt("count(post_no)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(��� �Խñ� �� ��ȸ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}

	/** �ش� ȸ���� ��� �� ��ȸ */
	public int selectCommentCnt(int memberNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select count(comment_no) ");
		sql.append("from comments_tb ");
		sql.append("where member_no=?");

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, memberNo);			
			rs = stmt.executeQuery();
			while(rs.next()){
				return rs.getInt("count(comment_no)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(��� ��� �� ��ȸ����) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}	
}
