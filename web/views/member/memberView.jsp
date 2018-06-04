<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ include file="/views/common/header.jsp"%>

<%
	Member member=(Member)request.getAttribute("member");
	String hobby=member.getHobby();
	String[] hobbys=hobby.split(",");
	String[] checked=new String[5];
	for(int i=0;i<hobbys.length;i++){
		switch(hobbys[i]){
		case "운동" : checked[0]="checked";break;
		case "등산" : checked[1]="checked";break;
		case "독서" : checked[2]="checked";break;
		case "게임" : checked[3]="checked";break;
		case "여행" : checked[4]="checked";break;
		}
	}
%>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		function fn_update_member(){
			var frm=$("#memberfrm");
			var url="<%=request.getContextPath()%>/memberUpdate";
			frm.attr("action",url);
			frm.submit();
		}
		function fn_delete_member(){
			var frm=$("#memberfrm");
			var url="<%=request.getContextPath()%>/memberDelete";
			frm.attr("action",url);
			frm.submit();
		}
		function fn_update_password(){
			var url="<%=request.getContextPath()%>/updatePassword?userId=<%=member.getUserId()%>";
			var status="left=500px, top=200px, width=400px, height=210px";
			var title="updatePassword";
			
			var popup=window.open(url,title,status);
		}
	</script>
	<section id="enroll-container">
		<h2>회원 정보 수정</h2>
		<form id="memberfrm" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text"name="userId_" id="userId_" value="<%=member.getUserId()%>" readonly>
					<br></td>
				</tr>
				<%-- <tr>
					<th>비밀번호</th>
					<td><input type="password" name="password_" id="password_" 
					value="<%=member.getPassword()%>"required><br></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="password2_" id="password2_" 
					value="<%=member.getPassword()%>" required><br></td>
				</tr>
				<tr> --%>
					<th>이름</th>
					<td><input type="text" name="userName" id="userName" 
					value="<%=member.getUserName()%>" required><br></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" id="age"
					value="<%=member.getAge()%>" ><br></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" placeholder="abc@ab.com" id="email"
					value="<%=member.getEmail()%>" ><br></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="phone" placeholder="-(없이)01000000000" 
					id="phone" maxlength=11 value="<%=member.getPhone()%>" required><br></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" id="address"
					value="<%=member.getAddress()%>" ><br></td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
					<%if("M".equals(member.getGender())){ %>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
					<%}else{ %>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" checked>
					<label for="gender1">여</label>
					<%} %>
					<br></td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=checked[0] %>>
					<label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" <%=checked[1] %>>
					<label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" <%=checked[2] %>>
					<label for="hobby2">독서</label>
					<input type="checkbox" name="hobby" id="hobby3" value="게임" <%=checked[3] %>>
					<label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" <%=checked[4] %>>
					<label for="hobby4">여행</label><br></td>
				</tr>
			</table>
			<input type="button" onclick="fn_update_member();" value="정보수정">
			<input type="button" onclick="fn_update_password();" value="비밀번호변경">
			<input type="button" onclick="fn_delete_member();" value="탈퇴">
		</form>
	</section>
<%@ include file="/views/common/footer.jsp"%>