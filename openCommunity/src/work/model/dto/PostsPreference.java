package work.model.dto;

import java.io.Serializable;

public class PostsPreference extends Posts implements Serializable{
	
	/** 회원 번호 */
	private int memberNo;
	/** 게시판 번호 */
	private int boardNo;
	/** 게시글 번호 */
	private int postNo;
	/** 추천수
	 * 추천 : 양수 / 반대 : 음수
	 */
	private int recommend;
	
	public PostsPreference () {}
	
	public PostsPreference(int memberNo, String postTitle, String createTime, int postViews, int recommend) {
		super(memberNo,postTitle, createTime, postViews);
		// TODO Auto-generated constructor stub
		this.recommend = recommend;
	}

	public PostsPreference(int memberNo, int boardNo, int postNo, String postTitle, String postContent,
			String createTime, int postViews) {
		super(memberNo, boardNo, postNo, postTitle, postContent, createTime, postViews);
		// TODO Auto-generated constructor stub
	}
	// 상속 전체 생성자
	public PostsPreference(int memberNo, int boardNo, int postNo, String postTitle, String postContent,
			String createTime, int postViews, int recommend) {
		super(memberNo, boardNo, postNo, postTitle, postContent, createTime, postViews);
		this.recommend = recommend;
		// TODO Auto-generated constructor stub
	}

	// 전체 생성자
	public PostsPreference(int memberNo, int boardNo, int postNo, int recommend) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.recommend = recommend;
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

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
}
