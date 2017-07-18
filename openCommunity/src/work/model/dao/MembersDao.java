package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import work.model.dto.Members;

public class MembersDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	public Connection getConnection() {
		return factory.getConnection();
	}
	// 전체회원조회 (Read: 읽기) 
	// + 가입일 최종 로그인, 마일리지
	public ArrayList<Members> selectList() {
		ArrayList<Members> list = new ArrayList<Members>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from members_tb";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int memberNo = 0;
			String memberEmail = null;
			String memberNickname = null;
			Members dto = null;
			
			while(rs.next()) {
				memberNo = rs.getInt("member_no");
				memberEmail = rs.getString("member_email");
				memberNickname= rs.getString("member_nickname");

				
				dto = new Members(memberNo, memberEmail, memberNickname);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(전체회원조회오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		
		return list;
	}
    // 회원삭제
	
	// 회원등록 생성 (Create:등록)
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
			System.out.println("Error(회원등록 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}		
	// 회원정보변경 (Update: 갱신)
	public int update(Members dto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("update members_tb set");
		sql.append("member_no=?, member_email=?, member_nickname=?, member_pw=?");
		sql.append("where member_no=?");
		//String SQL = "insert into members values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
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
			System.out.println("Error(회원 내정보 변경 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}	
	
	// 회원탈퇴 (Delete: 삭제)
	public int delete(int memberNo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "delete members_tb where member_no=?";		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, memberNo);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(회원탈퇴 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}	
		return 0;
	}
	
	// 로그인
	public int loginCheck(String memberEmail, String memberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select member_no from members where member_email=? and member_pw=?";
		
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
			System.out.println("Error(로그인오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return 0;
	}
	// 암호찾기 -> 찾앗을경우 임시비밀번호 발송
	// 
	// 관리자 : 회원 전체정보 변경
}
