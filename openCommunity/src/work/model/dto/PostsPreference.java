package work.model.dto;

import java.io.Serializable;

public class PostsPreference implements Serializable{
	
	/** ȸ�� ��ȣ */
	private int memberNo;
	/** �Խ��� ��ȣ */
	private int boardNo;
	/** �Խñ� ��ȣ */
	private int postNo;
	/** ��õ��
	 * ��õ : ��� / �ݴ� : ����
	 */
	private int recommend;
	
	public PostsPreference () {}
	
	// ��ü ������
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
