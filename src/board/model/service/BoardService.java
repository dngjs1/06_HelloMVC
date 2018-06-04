package board.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardComment;

public class BoardService {
	
	public List<Board> selectList(int cPage,int numPerPage){
		Connection conn=getConnection();
		List<Board> b=new BoardDao().selectList(conn,cPage,numPerPage);
		close(conn);
		return b;
	}
	public int selectCount() {
		Connection conn=getConnection();
		int cnt=new BoardDao().selectCount(conn);
		close(conn);
		return cnt;
	}
	public Board selectOne(int no) {
		Connection conn=getConnection();
		Board b=new BoardDao().selectOne(conn,no);
		close(conn);
		return b;
	}
	public void incrementCount(int no) {
		Connection conn=getConnection();
		int result = new BoardDao().incrementCount(conn,no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=new BoardDao().insertBoard(conn,b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int insertComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=new BoardDao().insertComment(conn,bc);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public List<BoardComment> selectcomment(int no){
		Connection conn=getConnection();
		List<BoardComment> list=new BoardDao().selectcomment(conn,no);
		close(conn);
		return list;
	}
}
