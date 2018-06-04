package board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String originalFileName;
	private String renameFileName;
	private Date boardDate;
	private int boardCount;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, String originalFileName,
			String renameFileName, Date boardDate, int boardCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.boardDate = boardDate;
		this.boardCount = boardCount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", originalFileName=" + originalFileName + ", renameFileName="
				+ renameFileName + ", boardDate=" + boardDate + ", boardCount=" + boardCount + "]";
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
	 * @return the boardWriter
	 */
	public String getBoardWriter() {
		return boardWriter;
	}
	/**
	 * @param boardWriter the boardWriter to set
	 */
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	/**
	 * @return the boardContent
	 */
	public String getBoardContent() {
		return boardContent;
	}
	/**
	 * @param boardContent the boardContent to set
	 */
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	/**
	 * @return the originalFileName
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}
	/**
	 * @param originalFileName the originalFileName to set
	 */
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	/**
	 * @return the renameFileName
	 */
	public String getRenameFileName() {
		return renameFileName;
	}
	/**
	 * @param renameFileName the renameFileName to set
	 */
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	/**
	 * @return the boardDate
	 */
	public Date getBoardDate() {
		return boardDate;
	}
	/**
	 * @param boardDate the boardDate to set
	 */
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	/**
	 * @return the boardCount
	 */
	public int getBoardCount() {
		return boardCount;
	}
	/**
	 * @param boardCount the boardCount to set
	 */
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	
	
}
