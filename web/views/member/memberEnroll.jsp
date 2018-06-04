<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
	<section id="enroll-container">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

		<script>
			$(function(){
		      $("#password2_").blur(function(){
		    	  var p1=$("#password_").val();
		    	  var p2=$("#password2_").val();
		    	  if(p1 != p2){
		    		  alert("페스워드가 일치하지 않습니다.");
		    		  $("#password_").focus();
		    		  $("#password_").val("");
		    		  $("#password2_").val("");
		    	  }
		      });
		    });
			
			function fn_enroll_validate(){
				var userId=$("#userId_");
				if(userId.val().length<4){
					alert("아이디는 최소 4자리 이상이여야 합니다.");
					userId.focus();
					return false;
				}
				return true;
			}
			//중복검사를 위한 별도의 서블릿 요청 함수
			function fn_checkIdDuplicate(){
			//아이디가 있는지 체크~
			var userId=$("#userId_").val().trim();
			//javascript에서 null==false 인식
			if(!userId||userId.length<4){
				alert("아이디는 입력하거나 4자 이상 입력하세요");
				return;
			}
			var url="<%=request.getContextPath()%>/checkIdDuplicate";
			var title="checkIdduplicate";
			var status="left=500px,top=100px,width=300px,height=200px";
			var popup=window.open("",title,status);
			
			checkIdDuplicateFrm.userId.value=userId;
			checkIdDuplicateFrm.target=title;
			checkIdDuplicateFrm.action=url;
			checkIdDuplicateFrm.method="post";
			checkIdDuplicateFrm.submit();
		}
		</script>
		
		<h2>회원가입정보 입력</h2>
		<form action="memberEnrollEnd" method="post" onsubmit="return fn_enroll_validate();">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" placeholder="4글자 이상" name="userId_" id="userId_" required>
					<input type="button" value="중복검사" onclick="fn_checkIdDuplicate();"/><br></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password_" id="password_" required><br></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="password2_" id="password2_" required><br></td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" id="userName" required><br></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" id="age"><br></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" placeholder="abc@ab.com" id="email"><br></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="phone" placeholder="-(없이)01000000000" id="phone" maxlength=11 required><br></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" id="address"><br></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" checked>
					<label for="gender1">여</label><br></td>
				</tr>
				<tr>
					<th>취미</th>
					<td><input type="checkbox" name="hobby" id="hobby0" value="운동">
					<label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산">
					<label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서">
					<label for="hobby2">독서</label>
					<input type="checkbox" name="hobby" id="hobby3" value="게임">
					<label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행">
					<label for="hobby4">여행</label><br></td>
				</tr>
			</table>
			<input type="submit" value="가입">
			<input type="reset" value="취소">
		</form>
		<form name="checkIdDuplicateFrm" method="post">
			<input type="hidden" name="userId">
		</form>
	</section>
<%@ include file="/views/common/footer.jsp"%>