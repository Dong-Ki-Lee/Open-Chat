package work.model.service;

import work.model.dao.BoardSearchDao;

public class SearchService {
	
	private BoardSearchDao dao = new BoardSearchDao();
	
	public int getBoardQuantity(int boardNo) {
		return dao.getBoardQuantity(boardNo);
	}
}
