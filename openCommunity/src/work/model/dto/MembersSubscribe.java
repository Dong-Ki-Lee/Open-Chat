package work.model.dto;
import java.io.Serializable;

public class MembersSubscribe implements Serializable {
	/*ȸ�� �ѹ� ���� */
	private int memberNo;
	/*ȸ�� �Խ��� �ѹ� ���� */
	private int memberBoardNo;
	
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
	public MembersSubscribe(int memberNo, int memberBoardNo) {
		super();
		this.memberNo = memberNo;
		this.memberBoardNo = memberBoardNo;
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
	 * @return the memberBoardNo
	 */
	public int getMemberBoardNo() {
		return memberBoardNo;
	}

	/**
	 * @param memberBoardNo the memberBoardNo to set
	 */
	public void setMemberBoardNo(int memberBoardNo) {
		this.memberBoardNo = memberBoardNo;
	}

}
