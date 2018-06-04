package notice.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {

	public List<Notice> selectList(){
		Connection conn=getConnection();
		List<Notice> list=new NoticeDao().selectList(conn);
		close(conn);
		return list;
	}
	public Notice selectOne(int no) {
		Connection conn=getConnection();
		Notice n=new NoticeDao().selectOne(conn,no);
		close(conn);
		return n;
	}
	public int updateNotice(Notice n) {
		Connection conn=getConnection();
		int result=new NoticeDao().updateNotice(conn,n);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int insertNotice(Notice n) {
		Connection conn=getConnection();
		int result=new NoticeDao().insertNotice(conn,n);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int deleteNotice(int no) {
		Connection conn=getConnection();
		int result=new NoticeDao().deleteNotice(conn,no);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
