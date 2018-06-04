<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,notice.model.vo.Notice" %>

<%
	ArrayList<Notice> list=(ArrayList)request.getAttribute("noticeList");
%>
    
<style>
    div#notice-container{width:600px; margin:0 auto; text-align:center;}
    div#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
    table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;} 
    input#btn-add{float:right; margin:0 0 15px;}
    
</style>

<%@ include file="/views/common/header.jsp" %>
    
	<div id="notice-container">
		<h2>공지사항</h2>
		<%if(memberLoggedIn!=null&&"admin".equals(memberLoggedIn.getUserId())) {%>
			<input type='button' value='글쓰기' id='btn-add' onclick='fn_addNotice()'>
		<%} %>
		<table id="tbl-notice">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>첨부파일</th>
					<th>작성일</th>
				</tr>
			</thead>
			<script>
				function fn_addNotice(){
					location.href="<%=request.getContextPath()%>/noticeAdd";
				}
			</script>
			<tbody>
			<%for(Notice n:list){%>
				<tr>
					<td><%=n.getNoticeNo() %></td>
					<td><a href="<%=request.getContextPath()%>
					/noticeView?no=<%=n.getNoticeNo()%>"><%=n.getNoticeTitle() %></a></td>
					<td><%=n.getNoticeWriter() %></td>
					<td>
						<%if(n.getFilePath()!=null){ %>
						<img alt='첨부파일' src='<%=request.getContextPath()%>/image/file.png' width='16px'>
						<% } %>
					</td>
					<td><%=n.getNoticeDate() %></td>
				</tr>
			<%}%>
			</tbody>
		</table>
	</div>
<%@ include file="/views/common/footer.jsp" %>