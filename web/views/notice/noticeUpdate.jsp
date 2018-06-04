<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice" %>
<%
	Notice n=(Notice)request.getAttribute("notice");
%>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function validate(){
		if($("#title").val().trim().length==0)
		{
			alert("제목을 입력하세요.");
			$("#title").focus();
			return false;
		}
		if($("#content").val().trim().length==0)
		{
			alert("내용을 입력하세요.");
			$("#content").focus();
			return false;
		}
		return true;
	}
</script>

<%@ include file="/views/common/header.jsp" %>

<div id="notice-container">
	<form action="<%=request.getContextPath()%>/noticeupdateEnd" method="post" enctype="multipart/form-data">
        <table id="tbl-notice">
	        <tr>
	            <th>글번호</th>
	            <td><input type="text" name="no" value="<%=n.getNoticeNo() %>" required readonly></td>
	        </tr>
	        <tr>
	            <th>제 목</th>
	            <td><input type="text" id="title" name="title" value="<%=n.getNoticeTitle() %>" required></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><input type="text" name="writer" value=<%=n.getNoticeWriter()%> readonly required/></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td style="position:relative;">
	                <%if(n.getFilePath()!=null){ %>
	                    <input type="file" name="up_file" value="<%=n.getFilePath() %>">
	                <%} else { %>
	                    <input type="file" name="up_file">
	                <%} %>
	                <input type="hidden" name="old_file" value="<%=n.getFilePath() %>">
	            </td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td><textarea rows="5" id="content" cols="50" name="content"><%=n.getNoticeContent() %></textarea></td>
	        </tr>
	        <tr>
	            <th colspan="2">
	                <input type="submit" value="등록하기" onclick="return validate();">
	            </th>
	        </tr>
    	</table>
    </form>
</div>


<style>
	div#notice-container{width:600px; margin:0 auto; text-align:center;}
	div#notice-container h2{margin:10px 0;}
	table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>	

<%@ include file="/views/common/footer.jsp" %>