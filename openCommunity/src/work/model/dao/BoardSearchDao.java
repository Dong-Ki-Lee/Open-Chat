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
		ArrayList<NoticeBoards> returnList = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from notice_boards_tb where board_title like %?%";
		
		String[] tagArray = input.split("#");
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < tagArray.length; i++) {
				stmt.setString(1, tagArray[i]);
				rs = stmt.executeQuery(sql);

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
			
			DescendingObj descending = new DescendingObj();
			Collections.sort(list, descending);
			
			HashSet hs = new HashSet(list);
			returnList = new ArrayList<NoticeBoards>(hs);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error(게시판 조회 오류) : " + e.getMessage());
		} finally {
			factory.close(rs, stmt, conn);
		}
		
		return returnList;
	}
	


}


class DescendingObj implements Comparator<NoticeBoards> {
	 
    @Override
    public int compare(NoticeBoards first, NoticeBoards second) {
    	
    	int firstCount = 0;
    	int secondCount = 0;
    	//우선순위 구현
        return 0;
    }
 
}
