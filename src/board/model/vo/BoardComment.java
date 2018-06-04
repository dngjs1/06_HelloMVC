package board.model.vo;

import java.sql.Date;

public class BoardComment {
	private int boardCommentNo;
	private int boardCommentLevel;
	private String boardCommentWriter;
	private String boardCommentContent;
	private int boardRef;
	private int boardCommentRef;
	private Date boardCommentDate;
	
	public BoardComment() {
		// TODO Auto-generated constructor stub
	}

	public BoardComment(int boardCommentNo, int boardCommentLevel, String boardCommentWriter,
			String boardCommentContent, int boardRef, int boardCommentRef, Date boardCommentDate) {
		super();
		this.boardCommentNo = boardCommentNo;
		this.boardCommentLevel = boardCommentLevel;
		this.boardCommentWriter = boardCommentWriter;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
		this.boardCommentDate = boardCommentDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoardComment [boardCommentNo=" + boardCommentNo + ", boardCommentLevel=" + boardCommentLevel
				+ ", boardCommentWriter=" + boardCommentWriter + ", boardCommentContent=" + boardCommentContent
				+ ", boardRef=" + boardRef + ", boardCommentRef=" + boardCommentRef + ", boardCommentDate="
				+ boardCommentDate + "]";
	}

	/**
	 * @return the boardCommentNo
	 */
	public int getBoardCommentNo() {
		return boardCommentNo;
	}

	/**
	 * @param boardCommentNo the boardCommentNo to set
	 */
	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}

	/**
	 * @return the boardCommentLevel
	 */
	public int getBoardCommentLevel() {
		return boardCommentLevel;
	}

	/**
	 * @param boardCommentLevel the boardCommentLevel to set
	 */
	public void setBoardCommentLevel(int boardCommentLevel) {
		this.boardCommentLevel = boardCommentLevel;
	}

	/**
	 * @return the boardCommentWriter
	 */
	public String getBoardCommentWriter() {
		return boardCommentWriter;
	}

	/**
	 * @param boardCommentWriter the boardCommentWriter to set
	 */
	public void setBoardCommentWriter(String boardCommentWriter) {
		this.boardCommentWriter = boardCommentWriter;
	}

	/**
	 * @return the boardCommentContent
	 */
	public String getBoardCommentContent() {
		return boardCommentContent;
	}

	/**
	 * @param boardCommentContent the boardCommentContent to set
	 */
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}

	/**
	 * @return the boardRef
	 */
	public int getBoardRef() {
		return boardRef;
	}

	/**
	 * @param boardRef the boardRef to set
	 */
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}

	/**
	 * @return the boardCommentRef
	 */
	public int getBoardCommentRef() {
		return boardCommentRef;
	}

	/**
	 * @param boardCommentRef the boardCommentRef to set
	 */
	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}

	/**
	 * @return the boardCommentDate
	 */
	public Date getBoardCommentDate() {
		return boardCommentDate;
	}

	/**
	 * @param boardCommentDate the boardCommentDate to set
	 */
	public void setBoardCommentDate(Date boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}
	
	
}
