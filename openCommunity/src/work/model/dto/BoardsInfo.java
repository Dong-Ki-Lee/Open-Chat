package work.model.dto;

public class BoardsInfo extends NoticeBoards {
	
	/** 게시글 수 */
	private int postsCnt;
	
	/** 구독 수 */
	private int subscribeCnt;

	/**
	 * @param boardNo
	 * @param boardTitle
	 * @param boardTag
	 */
	public BoardsInfo(int boardNo, String boardTitle, int postsCnt, int subscribeCnt) {
		super(boardNo, boardTitle, null);
		this.postsCnt = postsCnt;
		this.subscribeCnt = subscribeCnt;
	}
	
	

	/**
	 * @return the postsCnt
	 */
	public int getPostsCnt() {
		return postsCnt;
	}

	/**
	 * @param postsCnt the postsCnt to set
	 */
	public void setPostsCnt(int postsCnt) {
		this.postsCnt = postsCnt;
	}

	/**
	 * @return the subscribeCnt
	 */
	public int getSubscribeCnt() {
		return subscribeCnt;
	}

	/**
	 * @param subscribeCnt the subscribeCnt to set
	 */
	public void setSubscribeCnt(int subscribeCnt) {
		this.subscribeCnt = subscribeCnt;
	}
	
	
}
