package work.model.dto;

import java.io.Serializable;

public class Posts extends NoticeBoards implements Serializable {
	private int memberNo;
	private int boardNo;
	private int postNo;
	private String postTitle;
	private String postContent;
	private String createTime;
	private int postViews;
	
	private int disBoardCnt;
	
	public Posts() {
	}
	
	
	
	// not null
	public Posts(int memberNo, int boardNo, int postNo, String postTitle, String createTime, int postViews) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.createTime = createTime;
		this.postViews = postViews;
	}
	
	
	/**
	 * �Խù� ��ȸ ������ ������
	 * @param postNo
	 * @param postTitle
	 * @param postContent
	 * @param createTime
	 * @param postViews
	 */
	public Posts(int postNo, String postTitle, String postContent, String createTime, int postViews) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createTime = createTime;
		this.postViews = postViews;
	}

	// ��ü ������
	public Posts(int memberNo, int boardNo, int postNo, String postTitle, String postContent, String createTime,
			int postViews) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createTime = createTime;
		this.postViews = postViews;
	}
	
	

	/**
	 * �Ű�� ������
	 * @param boardNo
	 * @param boardTitle
	 * @param boardTag
	 */
	public Posts(int boardNo, String boardTitle, String postTitle, String postContent, String createTime, int disBoardCnt) {
		super(boardNo, boardTitle, null);
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createTime = createTime;
		this.disBoardCnt = disBoardCnt;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getPostViews() {
		return postViews;
	}

	public void setPostViews(int postViews) {
		this.postViews = postViews;
	}



	/**
	 * @return the disBoardCnt
	 */
	public int getDisBoardCnt() {
		return disBoardCnt;
	}



	/**
	 * @param disBoardCnt the disBoardCnt to set
	 */
	public void setDisBoardCnt(int disBoardCnt) {
		this.disBoardCnt = disBoardCnt;
	}
	
	
	
	
}
