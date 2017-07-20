package work.model.dto;

import java.io.Serializable;

public class PostInfo extends NoticeBoards implements Serializable {


	private int memberNo;
	private int boardNo;
	private int postNo;
	private String postTitle;
	private String postContent;
	private String createTime;
	private int postViews;

	private int disBoardCnt;

	public PostInfo() {}


	/**
	 * @param memberNo
	 * @param boardNo
	 * @param postNo
	 * @param postTitle
	 * @param postContent
	 * @param createTime
	 * @param postViews
	 */
	public PostInfo(int memberNo, int boardNo, int postNo, String postTitle, String postContent, String createTime,
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
	 * 신고글 데이터
	 * @param boardNo
	 * @param boardTitle
	 * @param boardTag
	 */
	public PostInfo(int boardNo, String boardTitle, String postTitle, String postContent, String createTime, int disBoardCnt) {
		super(boardNo, boardTitle, null);
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createTime = createTime;
		this.disBoardCnt = disBoardCnt;
	}

	/**
	 * @param memberNo
	 * @param postNo
	 * @param postTitle
	 * @param postContent
	 * @param createTime
	 * @param postViews
	 */
	public PostInfo(int memberNo, int postNo, String postTitle, String postContent, String createTime, int postViews) {
		super();
		this.memberNo = memberNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createTime = createTime;
		this.postViews = postViews;
	}


	/**
	 * @return the postNo
	 */
	public int getPostNo() {
		return postNo;
	}

	/**
	 * @param postNo the postNo to set
	 */
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	/**
	 * @return the postTitle
	 */
	public String getPostTitle() {
		return postTitle;
	}

	/**
	 * @param postTitle the postTitle to set
	 */
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	/**
	 * @return the postContent
	 */
	public String getPostContent() {
		return postContent;
	}

	/**
	 * @param postContent the postContent to set
	 */
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	/**
	 * @return the memberNo
	 */
	public int getMemberNo() {
		return memberNo;
	}

	/**
	 * @param memberNo the memberNo to set
	 */
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the postViews
	 */
	public int getPostViews() {
		return postViews;
	}

	/**
	 * @param postViews the postViews to set
	 */
	public void setPostViews(int postViews) {
		this.postViews = postViews;
	}


	/**
	 * @return the boardNo
	 */
	public int getBoardNo() {
		return boardNo;
	}


	/**
	 * @param boardNo the boardNo to set
	 */
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}





}
