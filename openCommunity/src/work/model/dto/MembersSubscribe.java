package work.model.dto;
import java.io.Serializable;

public class MembersSubscribe implements Serializable {
	/*ȸ�� �ѹ� ���� */
	private int memberNo;
	/*ȸ�� �Խ��� �ѹ� ���� */
	private int boardNo;
	
	/**
	 * 
	 */
	public MembersSubscribe() {
		super();
	}

	/**
	 * @param memberNo
	 * @param memberBoardNo
	 */
	public MembersSubscribe(int memberNo, int boardNo) {
		super();
		this.memberNo = memberNo;
		this.boardNo = boardNo;
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

	
}
