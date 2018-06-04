package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	//1.DB접속정보를 경로 확인, conn객체 생성/반환
	//2.처리된 수정, 삭제, 삽입에 대한 commit,rollback 처리
	//3.dao에서 받은 결과를 controller에 전달.
	
	//로그인 결과를 확인하는 변수 선언
	public static int LOGIN_OK=1;
	public static int WRONG_PASSWORD=0;
	public static int ID_NOT_EXIST=-1;
	
	public int loginCheck(String userId,String password) {
		Connection conn=getConnection();
		int result=new MemberDao().loginCheck(conn,userId,password);
		close(conn);
		return result;
	}
	public Member selectOne(String userId) {
		Connection conn=getConnection();
		Member m=new MemberDao().selectOne(conn,userId);
		close(conn);
		return m;
	}
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=new MemberDao().insertMember(conn,m);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int updateMember(Member m) {
		Connection conn=getConnection();
		int result=new MemberDao().updateMember(conn,m);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int deleteMember(String userId) {
		Connection conn=getConnection();
		int result=new MemberDao().deleteMember(conn,userId);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public boolean duplicateId(String userId) {
		Connection conn=getConnection();
		boolean flag=new MemberDao().duplicateId(conn,userId);
		close(conn);
		return flag;
	}
	public int updatePassword(String userId,String password) {
		Connection conn=getConnection();
		int result=new MemberDao().updatePassword(conn,userId,password);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
