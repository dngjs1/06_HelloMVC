package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class AdminDao {
	
	Properties prop = new Properties();
	
	public AdminDao() {
		try {
			String fileName=MemberDao.class.getResource("/sql/admin/admin-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Member> selectMemberList(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectPage");
		ArrayList<Member> memberlist=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ((cPage-1)*numPerPage+1));
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String userid=rs.getString("userid");
				String password=rs.getString("password");
				String username=rs.getString("username");
				String gender=rs.getString("gender");
				int age=rs.getInt("age");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				String hobby=rs.getString("hobby");
				Date enrolldate=rs.getDate("enrolldate");
				memberlist.add(new Member(userid,password,username,gender
						,age,email,phone,address,hobby,enrolldate));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberlist;
	}
	public List<Member> selectMemberById(Connection conn,String keyword,int cPage,int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectMemberId");
		ArrayList<Member> memberlist=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2, ((cPage-1)*numPerPage+1));
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String userid=rs.getString("userid");
				String password=rs.getString("password");
				String username=rs.getString("username");
				String gender=rs.getString("gender");
				int age=rs.getInt("age");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				String hobby=rs.getString("hobby");
				Date enrolldate=rs.getDate("enrolldate");
				memberlist.add(new Member(userid,password,username,gender
						,age,email,phone,address,hobby,enrolldate));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberlist;
	}
	public List<Member> selectMemberByName(Connection conn,String keyword,int cPage,int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectMemberName");
		ArrayList<Member> memberlist=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			pstmt.setInt(2, ((cPage-1)*numPerPage+1));
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String userid=rs.getString("userid");
				String password=rs.getString("password");
				String username=rs.getString("username");
				String gender=rs.getString("gender");
				int age=rs.getInt("age");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				String hobby=rs.getString("hobby");
				Date enrolldate=rs.getDate("enrolldate");
				memberlist.add(new Member(userid,password,username,gender
						,age,email,phone,address,hobby,enrolldate));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberlist;
	}
	public List<Member> selectMemberByGender(Connection conn,String keyword,int cPage,int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=prop.getProperty("selectMemberGender");
		ArrayList<Member> memberlist=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			pstmt.setInt(2, ((cPage-1)*numPerPage+1));
			pstmt.setInt(3, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String userid=rs.getString("userid");
				String password=rs.getString("password");
				String username=rs.getString("username");
				String gender=rs.getString("gender");
				int age=rs.getInt("age");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				String hobby=rs.getString("hobby");
				Date enrolldate=rs.getDate("enrolldate");
				memberlist.add(new Member(userid,password,username,gender
						,age,email,phone,address,hobby,enrolldate));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return memberlist;
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
	public int selectCountByUserId(Connection conn,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectCountUserId");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
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
	public int selectCountByUserName(Connection conn,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectCountUserName");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
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
	public int selectCountByGender(Connection conn,String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("selectCountGender");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
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
}
