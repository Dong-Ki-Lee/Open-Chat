package work.model.dto;

import java.io.Serializable;

public class Posts extends Members implements Serializable{
	private int memberNo;
	private int boardNo;
	private int postNo;
	private String postTitle;
	private String postContent;
	private String createTime;
	private int postViews;
	
	public Posts() {}
	
	
	
	//
	public Posts(int memberNo, int boardNo, int postNo, String postTitle, String postContent) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
	}

	


	public Posts(int memberNo, String memberEmail, String memberNickname, String memberPw,
			int boardNo, int postNo, String postTitle, String postContent, String createTime, int postViews) {
		super(memberNo, memberEmail, memberNickname, memberPw);
		// TODO Auto-generated constructor stub
		this.boardNo=boardNo;
		this.postNo=postNo;
		this.postTitle=postTitle;
		this.postContent=postContent;
		this.createTime=createTime;
		this.postViews=postViews;
	}



	public Posts(int memberNo,String postTitle, String createTime, int postViews) {
		super();
		this.memberNo = memberNo;
		this.postTitle = postTitle;
		this.createTime = createTime;
		this.postViews = postViews;
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
	 * 게시물 조회 데이터 생성자
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

	// 전체 생성자
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



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(memberNo);
		builder.append(", ");
		builder.append(boardNo);
		builder.append(", ");
		builder.append(postNo);
		builder.append(", ");
		builder.append(postTitle);
		builder.append(", ");
		builder.append(postContent);
		builder.append(", ");
		builder.append(createTime);
		builder.append(", ");
		builder.append(postViews);
		return builder.toString();
	}
	
	
	
}
