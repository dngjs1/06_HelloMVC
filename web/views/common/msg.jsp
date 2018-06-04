<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=request.getContextPath()+(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
%>
<script>
	alert("<%=msg%>");
	if(<%=script%>!=null){
		<%=script%>;
	}else{
		location.href="<%=loc%>";
	}
	<%-- <%=script%>!=null?<%=script%>:""; --%>
</script>