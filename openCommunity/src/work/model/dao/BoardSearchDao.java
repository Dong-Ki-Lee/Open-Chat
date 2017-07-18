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
	
	public ArrayList<NoticeBoards> searchBoardWithTag(String input) {
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
		
		String[] tagArray = input.split("#");
		
		for (int i = 0; i < tagArray.length; i++) {
			sql.append("select * from notice_boards_tb ");
			sql.append("where board_tag like '%?%' ");
			if(i != tagArray.length-1) {
				sql.append("union all");
			}
		}
		sql.append(") notice");
		sql.append("group by board_no ");
		sql.append("order by tag_match ");
		sql.append("desc) search_end, ");
		sql.append("notice_boards_tb boards ");
		sql.append("where search_end.board_no = boards.board_no;");
		
		try  {
			conn = getConnection();
			stmt = conn.prepareStatement(sql.toString());
			
			for (int i = 0; i < tagArray.length; i++) {
				stmt.setString(i+1, tagArray[i]);
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
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(..) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		return null;
	}
	


}