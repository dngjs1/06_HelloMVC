package notice.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import notice.model.vo.Notice;

public class NoticeDao {
	Properties prop = new Properties();
	
	public NoticeDao() {
		try {
			String fileName=NoticeDao.class.getResource("/sql/notice/notice-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Notice> selectList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectList");
		ArrayList<Notice> list=new ArrayList();
		Notice n=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				n=new Notice();
				n.setNoticeNo(rs.getInt("notice_no"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeContent(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
				n.setStatus(rs.getString("status"));
				list.add(n);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public Notice selectOne(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectOne");
		Notice n=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				n=new Notice();
				n.setNoticeNo(rs.getInt("notice_no"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeContent(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
				n.setStatus(rs.getString("status"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return n;
	}
	public int updateNotice(Connection conn,Notice n) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateNotice");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilePath());
			pstmt.setInt(5, n.getNoticeNo());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int insertNotice(Connection conn,Notice n) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertNotice");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilePath());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteNotice(Connection conn,int no) {
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteNotice");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
