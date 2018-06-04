package notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private Date noticeDate;
	private String filePath;
	private String status;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int noticeNo, String noticeTitle, String noticeWriter, String noticeContent, Date noticeDate,
			String filePath, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
		this.noticeDate = noticeDate;
		this.filePath = filePath;
		this.status = status;
	}

	/**
	 * @return the noticeNo
	 */
	public int getNoticeNo() {
		return noticeNo;
	}

	/**
	 * @param noticeNo the noticeNo to set
	 */
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	/**
	 * @return the noticeTitle
	 */
	public String getNoticeTitle() {
		return noticeTitle;
	}

	/**
	 * @param noticeTitle the noticeTitle to set
	 */
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	/**
	 * @return the noticeWriter
	 */
	public String getNoticeWriter() {
		return noticeWriter;
	}

	/**
	 * @param noticeWriter the noticeWriter to set
	 */
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	/**
	 * @return the noticeContent
	 */
	public String getNoticeContent() {
		return noticeContent;
	}

	/**
	 * @param noticeContent the noticeContent to set
	 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	/**
	 * @return the noticeDate
	 */
	public Date getNoticeDate() {
		return noticeDate;
	}

	/**
	 * @param noticeDate the noticeDate to set
	 */
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeWriter=" + noticeWriter
				+ ", noticeContent=" + noticeContent + ", noticeDate=" + noticeDate + ", filePath=" + filePath
				+ ", status=" + status + "]";
	}
	
	
}
