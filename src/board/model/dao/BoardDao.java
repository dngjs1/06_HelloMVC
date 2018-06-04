package board.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import board.model.vo.Board;
import board.model.vo.BoardComment;
import member.model.dao.MemberDao;
import static common.JDBCTemplate.*;

public class BoardDao {
	
	Properties prop = new Properties();
	
	public BoardDao() {
		try {
			String fileName=BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectList(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		ArrayList<Board> list = new ArrayList<>();
		String sql=prop.getProperty("selectPage");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ((cPage-1)*numPerPage+1));
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()){
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setOriginalFileName(rs.getString("board_original_filename"));
				b.setRenameFileName(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardCount(rs.getInt("board_readcount"));
				list.add(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	public int selectCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectCount");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("cnt");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public Board selectOne(Connection conn,int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		String sql=prop.getProperty("selectOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setOriginalFileName(rs.getString("board_original_filename"));
				b.setRenameFileName(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardCount(rs.getInt("board_readcount"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
	}
	public int incrementCount(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("incrementCount");
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
	public int insertBoard(Connection conn,Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getOriginalFileName());
			pstmt.setString(5, b.getRenameFileName());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int insertComment(Connection conn,BoardComment bc) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			//0값으로 설정하여서 제약조건에서 에러발생!
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public List<BoardComment> selectcomment(Connection conn,int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardComment bc=null;
		ArrayList<BoardComment> list=new ArrayList<>();
		//계층형 쿼리. 확인할 것.
		String sql=prop.getProperty("selectComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bc=new BoardComment();
				bc.setBoardCommentNo(rs.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rs.getInt("board_comment_level"));
				bc.setBoardCommentWriter(rs.getString("board_comment_writer"));
				bc.setBoardCommentContent(rs.getString("board_comment_content"));
				bc.setBoardRef(rs.getInt("board_ref"));
				bc.setBoardCommentRef(rs.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rs.getDate("board_comment_date"));
				list.add(bc);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
}
