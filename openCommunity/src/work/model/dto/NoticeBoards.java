package work.model.dto;

import java.io.Serializable;

public class NoticeBoards implements Serializable {

	private int boardNo;
	private String boardTitle;
	private String boardTag;
	/**
	 * 
	 */
	public NoticeBoards() {
		super();
	}
	/**
	 * @param boardNo
	 * @param boardTitle
	 * @param boardTag
	 */
	public NoticeBoards(int boardNo, String boardTitle, String boardTag) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardTag = boardTag;
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
	 * @return the boardTitle
	 */
	public String getBoardTitle() {
		return boardTitle;
	}
	/**
	 * @param boardTitle the boardTitle to set
	 */
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	/**
	 * @return the boardTag
	 */
	public String getBoardTag() {
		return boardTag;
	}
	/**
	 * @param boardTag the boardTag to set
	 */
	public void setBoardTag(String boardTag) {
		this.boardTag = boardTag;
	}
	
	
}
