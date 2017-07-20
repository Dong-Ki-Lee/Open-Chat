/**
 * 
 */
package work.model.dto;

import java.io.Serializable;

/**
 * @author limjinha
 *
 */
public class Comments extends Members implements Serializable{

	/** 회원 번호 */
	private int memberNo;

	/** 게시판 번호 */
	private int boardNo;

	/** 게시글 번호 */
	private int postNo;

	/** 댓글 번호 */  
	private int commentNo; 

	/** 댓글 내용 */
	private String content;

	/** 댓글 작성 시간 */
	private String createTime;


	/**
	 * 기본 생성자
	 */
	public Comments() {
		super();
	}

	

	
	

	public Comments(int memberNo, String memberEmail, String memberNickname, String memberPw,
			int boardNo, int postNo, String content, String createTime) {
		super(memberNo, memberEmail, memberNickname, memberPw);
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.content = content;
		this.createTime = createTime;
		// TODO Auto-generated constructor stub
	}






	public Comments(int memberNo, int boardNo, int postNo, String content, String createTime) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.content = content;
		this.createTime = createTime;
	}



	/**
	 * 전체 댓글 데이터 생성자
	 * @param memberNo
	 * @param boardNo
	 * @param postNo
	 * @param commentNo
	 * @param content
	 * @param createTime
	 */
	public Comments(int memberNo, int boardNo, int postNo, int commentNo, String content, String createTime) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.commentNo = commentNo;
		this.content = content;
		this.createTime = createTime;
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

	/**
	 * @return the commentNo
	 */
	public int getCommentNo() {
		return commentNo;
	}

	/**
	 * @param commentNo the commentNo to set
	 */
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comments [memberNo=" + memberNo + ", boardNo=" + boardNo + ", postNo=" + postNo + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}

}
