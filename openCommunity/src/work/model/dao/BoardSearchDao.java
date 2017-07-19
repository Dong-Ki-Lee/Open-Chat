package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import work.model.dto.NoticeBoards;

public class BoardSearchDao {

	private FactoryDao factory = FactoryDao.getInstance();
	
	public Connection getConnection() {
		return factory.getConnection();
	}
	
	/*
	 * 맴버 번호를 넣고 그 맴버가 구독하는 게시판을 받아옴
	 */
	public ArrayList<NoticeBoards> getSubscribeBoard(int memberNo) {
		ArrayList<NoticeBoards> list = new ArrayList<NoticeBoards>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select notice_boards_tb.board_no, board_title, board_tag ");
		sql.append("from ");
		sql.append("(select notice_board_no ");
		sql.append("from MEMBERS_SUBSCRIBE_TB ");
		sql.append("where member_no = ?) temp, notice_boards_tb ");
		sql.append("where temp.notice_board_no = notice_boards_tb.board_no");
		
		try  {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			
			int boardNo = 0;
			String boardTitle = null;
			String boardTag = null;
			NoticeBoards dto = null;
			
			while(rs.next()) {
				boardNo = rs.getInt("board_no");
				boardTitle = rs.getString("board_title");
				boardTag = rs.getString("board_tag");

				dto = new NoticeBoards(boardNo, boardTitle, boardTag);
				list.add(dto);
			}
			
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(구독정보 받아오기 실패) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	/*
	 * 맴버 번호를 넣고 검색 기록을 받아옴
	 */
	public ArrayList<String> getSearchHistory(int memberNo) {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select search_word ");
		sql.append("from ");
		sql.append("(select search_word, count(*) temp ");
		sql.append("from search_history_tb ");
		sql.append("where member_no=? ");
		sql.append("group by search_word ");
		sql.append("order by temp ");
		sql.append("desc) ");
		sql.append("where temp > 2");
		
		try  {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			
			String searchWord = null;
				
			while(rs.next()) {
				searchWord = rs.getString("search_word");
				list.add(searchWord);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(검색기록 탐색 실패) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	/*
	 * 검색 기록을 넣고 태그가 많이 매칭되는 게시판 정보를 가져옴
	 */
	public ArrayList<NoticeBoards> searchBoardWithTag(ArrayList<String> input) {
		ArrayList<NoticeBoards> list = new ArrayList<NoticeBoards>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select tag_match, search_end.board_no, board_title, board_tag ");
		sql.append("from ");
		sql.append("(select count(*) tag_match, board_no ");
		sql.append("from ");
		sql.append("(");
		
		//String[] tagArray = input.split("#");
		
		for (int i = 0; i < input.size(); i++) {
			sql.append("select * from notice_boards_tb ");
			sql.append("where board_tag like '%");
			sql.append(input.get(i));
			sql.append("%' ");
			if(i != input.size() - 1) {
				sql.append("union all ");
			}
		}
		sql.append(") notice ");
		sql.append("group by board_no ");
		sql.append("order by tag_match ");
		sql.append("desc) search_end, ");
		sql.append("notice_boards_tb boards ");
		sql.append("where search_end.board_no = boards.board_no");
		System.out.println(sql.toString());
		try  {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			
/*			for (int i = 0; i < input.size(); i++) {
				stmt.setString(i+1, input.get(i));
			}
*/
			rs = stmt.executeQuery();

			int boardNo = 0;
			String boardTitle = null;
			String boardTag = null;
			NoticeBoards dto = null;
			
			while(rs.next()) {
				boardNo = rs.getInt("board_no");
				boardTitle = rs.getString("board_title");
				boardTag = rs.getString("board_tag");

				dto = new NoticeBoards(boardNo, boardTitle, boardTag);
				list.add(dto);
			}
		
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(태그검색 실패) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	/*
	 * 가장 글이 많은 게시판을 가져옴
	 */
	public ArrayList<NoticeBoards> getLargeBoard() {
		
		ArrayList<NoticeBoards> list = new ArrayList<NoticeBoards>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("select search.board_no, board_title, board_tag ");
		sql.append("from ");
		sql.append("(select board_no ");
		sql.append("from posts_tb ");
		sql.append("group by board_no ");
		sql.append("order by count(*) ");
		sql.append("desc) search, notice_boards_tb ");
		sql.append("where search.board_no = notice_boards_tb.board_no");
		try  {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			
			rs = stmt.executeQuery();

			int boardNo = 0;
			String boardTitle = null;
			String boardTag = null;
			NoticeBoards dto = null;
			
			while(rs.next()) {
				boardNo = rs.getInt("board_no");
				boardTitle = rs.getString("board_title");
				boardTag = rs.getString("board_tag");

				dto = new NoticeBoards(boardNo, boardTitle, boardTag);
				list.add(dto);
			}
		
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(큰 게시판 받아오기 실패) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}

}