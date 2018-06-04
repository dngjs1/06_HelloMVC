package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberDao {
	private Properties prop=new Properties();
	
	public MemberDao() {
		try {
			String fileName=MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int loginCheck(Connection conn,String userId,String password) {
		int result=-1;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		try {
			sql=prop.getProperty("selectOne");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("userid").equals(userId)
						&&rs.getString("password").equals(password)) {
					result= 1;
				}else if(rs.getString("userid").equals(userId)) {
					result= 0;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public Member selectOne(Connection conn,String userId) {
		PreparedStatement pstmt=null;
		Member m=null;
		ResultSet rs=null;
		String sql="";
		try {
			sql=prop.getProperty("selectOne");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {//처음은 속성값이기때문에 rs.next()를 먼저해줌
				m=new Member();
				m.setUserId(rs.getString("userid"));
				m.setPassword(rs.getString("password"));
				m.setUserName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	public int insertMember(Connection conn,Member m) {
		PreparedStatement pstmt=null;
		String sql="";
		int result=0;
		try {
			sql=prop.getProperty("insert");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateMember(Connection conn,Member m) {
		PreparedStatement pstmt=null;
		String sql=null;
		int result=0;
		try {
			sql=prop.getProperty("update");
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, m.getPassword());
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getGender());
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getHobby());
			pstmt.setString(8, m.getUserId());
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteMember(Connection conn,String userId) {
		PreparedStatement pstmt=null;
		String sql=null;
		int result=0;
		try {
			sql=prop.getProperty("delete");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public boolean duplicateId(Connection conn,String userId) {
		boolean flag=false;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int cnt=0;
		String sql="";
		
		try {
			sql=prop.getProperty("checkIdDuplicate");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			if(cnt==0) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return flag;
	}
	public int updatePassword(Connection conn,String userId,String password) {
		PreparedStatement pstmt = null;
		String sql="";
		int result=0;
		try {
			sql=prop.getProperty("updatePassword");
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
