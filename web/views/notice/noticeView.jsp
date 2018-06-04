<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.Notice" %>
<%
	Notice n=(Notice)request.getAttribute("notice");
	String loc=request.getContextPath()+"/noticeList";
%>

<style>
 div#notice-container{width:600px; margin:0 auto; text-align:center;}
 div#notice-container h2{margin:10px 0;}
 table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
 table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
 table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>

<%@ include file="/views/common/header.jsp" %>
	<div id="notice-container">
		<h2>공지사항</h2>
		<table id="tbl-notice">
			<tr>
				<th>제목</th>
				<td>
					<%=n.getNoticeTitle() %>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<%=n.getNoticeWriter() %>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<% if(n.getFilePath()!=null){ %>
					<a href="<%=request.getContextPath()%>/noticeFileDownload?filePath=<%=n.getFilePath()%>">
                	<img alt='첨부파일' 
                	src='<%=request.getContextPath() %>/image/file.png'
                	width='16px'>
                	</a>
 					<% } %>       
				</td>
			</tr>
			<tr>
            	<th>내 용</th>
            	<td><textarea rows="5" cols="50" name="content" readonly><%=n.getNoticeContent() %></textarea></td>
        	</tr>
			<tr>
				<td colspan='2'>
					<input type="button" value="목록으로" onclick="location.href='<%=request.getContextPath()%>/noticeList'">
					<% if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getUserId())){ %>
					<input type='button' value='수정하기' onclick='fn_updateNotice()'>
					<input type='button' value='삭제하기' onclick='fn_deleteNotice()'>
					<%} %>
				</td>
				<script>
					function fn_updateNotice()
					{
						location.href="<%=request.getContextPath()%>/noticeUpdate?no=<%=n.getNoticeNo()%>";
					}
					function fn_deleteNotice()
					{
						location.href="<%=request.getContextPath()%>/noticeDelete?no=<%=n.getNoticeNo()%>&filePath=<%=n.getFilePath()%>";
					}
				</script>
			</tr>	
		</table>
	</div>
<%@ include file="/views/common/footer.jsp" %>