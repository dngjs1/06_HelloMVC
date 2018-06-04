package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import admin.model.dao.AdminDao;
import member.model.vo.Member;

public class AdminService {
	
	public ArrayList<Member> selectMemberList(int cPage,int numPerPage){
		Connection conn=getConnection();
		ArrayList<Member> memberlist = new AdminDao().selectMemberList(conn,cPage,numPerPage);
		close(conn);
		return memberlist;
	}
	public List<Member> selectMemberById(String keyword,int cPage,int numPerPage){
		Connection conn=getConnection();
		List<Member> memberlist = new AdminDao().selectMemberById(conn,keyword,cPage,numPerPage);
		close(conn);
		return memberlist;
	}
	public List<Member> selectMemberByName(String keyword,int cPage,int numPerPage){
		Connection conn=getConnection();
		List<Member> memberlist = new AdminDao().selectMemberByName(conn,keyword,cPage,numPerPage);
		close(conn);
		return memberlist;
	}
	public List<Member> selectMemberByGender(String keyword,int cPage,int numPerPage){
		Connection conn=getConnection();
		List<Member> memberlist = new AdminDao().selectMemberByGender(conn,keyword,cPage,numPerPage);
		close(conn);
		return memberlist;
	}
	public int selectCount() {
		Connection conn=getConnection();
		int result=new AdminDao().selectCount(conn);
		close(conn);
		return result;
	}
	public int selectCountByUserId(String keyword) {
		Connection conn=getConnection();
		int result=new AdminDao().selectCountByUserId(conn,keyword);
		close(conn);
		return result;
	}
	public int selectCountByUserName(String keyword) {
		Connection conn=getConnection();
		int result=new AdminDao().selectCountByUserName(conn,keyword);
		close(conn);
		return result;
	}
	public int selectCountByGender(String keyword) {
		Connection conn=getConnection();
		int result=new AdminDao().selectCountByGender(conn,keyword);
		close(conn);
		return result;
	}
}
