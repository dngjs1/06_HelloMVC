<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String userId=request.getParameter("userId"); 
   String script=(String)request.getAttribute("script");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 수정</title>
<style>
	div#updatePassword-container{
		background:lightgreen;
	}
	div#updatePassword-container table{
		margin:0 auto;
		border-spacing: 20px;
	}
	div#updatePassword-container table tr:last-of-type td{
		text-align:center;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function password_validate(){
		var pwd_new = $("#password_new").val().trim();
		var pwd_chk = $("#password_chk").val().trim();
		
		if(pwd_new!=pwd_chk){
			alert("입력한 비밀번호가 일치하지 않습니다.");
			$("#password_new").select();
			return false;
		}
		return true;
	}
	
</script>
</head>
<body>
	<div id="updatePassword-container">
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/updatePasswordEnd" method="post">
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td><input type="password" name="password_new" id="password_new" required></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="password_chk" id="password_chk" required><br></td>
				</tr>
				<td colspan="2">
					<input type="submit" onclick="return password_validate();" value="변경" >&nbsp;
					<input type="button" onclick="self.close();" value="닫기"></td>
			</table>
			<input type="hidden" name="userId" value="<%=userId %>">
		</form>
	</div>
</body>
</html>