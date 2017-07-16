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
	
	// �Խ��� ��� 
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
			System.out.println("Error (�Խ��� ���): " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		
		return 0;
	}
	
	// �Խù� ��� 
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
			System.out.println("Error (�Խñ� ���) : " + e.getMessage());
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// ��� ���
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
			System.out.println("Error (��� ���) : " + e.getMessage());
			// TODO: handle exception
		} finally {
			factory.close(stmt, conn);
		}
		return 0;
	}
	
	// �Խù� ��ȸ		+ ����
	// ��� ��ȸ	 + ����
	// ��� �Խñ� ��
	// ��� �� ��ȸ
	
	// �Խ��� ����
//	-- ���ϸ��� ����
//	-- Ű���� ���
//	�Խñ�
//	-- ��õ�� �ֽż� ���� ����
//	�ݴ�� �̻� �Ǹ� �� ���� -- �Խñ� / ���
}
