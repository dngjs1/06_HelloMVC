<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,member.model.vo.Member"%>
    <%
	    List<Member> list=(List<Member>)request.getAttribute("list");
	    String searchKeyword=(String)request.getAttribute("searchKeyword");
	    String searchType=(String)request.getAttribute("searchType");    
	    
	    int numPerPage=(int)request.getAttribute("numPerPage");
	    int cPage=(int)request.getAttribute("cPage");
	    String pageBar=(String)request.getAttribute("pageBar");
    %>
    
<%@ include file="/views/common/header.jsp"%>
    <style>
    section#memberList-container table#tbl-member{
		width:100%;
		border:1px solid gray;
		border-collapse:collapse;
	}
	section#memberList-container table#tbl-member th,table#tbl-member td{
		border:1px solid gray;
		padding:10px;
	}
	/*검색 로직구현 */
	div#search-container{
		margin:0 0 10px;
		padding : 3px;
		background-color:rgb(0,188,212,0.3);
	}
	div#search-userId{
		display:inline-block;
	}
	div#search-userName{
		display:none;
	}
	div#search-gender{
		display:none;
	}
	/*페이징 스타일 추가*/
	section#memberList-container div#neck-container{
		padding:0px;
		height:50px;
		background-color:rgba(0,188,212,0,0.3);
	}
	section#memberList-container div#search-container{
		margin:0 0 10px 0;
		padding:3px;
		float:left;
	}
	section#memberList-container div#numPerPage-container{
		float:right;
	}
	section#memberList-container form#numPerPageFrm{
		display:inline;
	}
    </style>
    <script>
    window.onload=function(){
    	var searchType=document.querySelector("#searchType");
    	var sid=document.querySelector("#search-userId");
		var sname=document.querySelector("#search-userName");
		var sgender=document.querySelector("#search-gender");
    	searchType.addEventListener("change", function(){
    		sid.style.display="none";
			sname.style.display="none";
			sgender.style.display="none";
			
			(document.querySelector("#search-"+this.value)).style.display='inline-block';
    	});
    	
    	//페이징 함수 처리
		var numPerPage=document.querySelector("#numPerPage");
		numPerPage.addEventListener("change",function(){
			document.numPerPageFrm.submit();
		});
    }  	

    </script>
    <section id="memberList-container">
		<h2>회원관리</h2>
		<div id='neck-container'>
			<div id='search-container'>
       		 검색타입 : 
		        <select id="searchType">
		            <option value="userId" <%="userId".equals(searchType)?"selected":""%>>아이디</option>		
		            <option value="userName" <%="userName".equals(searchType)?"selected":""%>>회원명</option>
		            <option value="gender" <%="gender".equals(searchType)?"selected":""%>>성별</option>
		        </select>
		        <div id="search-userId">
		            <form action="<%=request.getContextPath()%>/memberFinder">
		                <input type="hidden" name="searchType" value="userId"/>
		                <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." 
		                    value="<%="userId".equals(searchType)?searchKeyword:""%>" />
		                <button type="submit">검색</button>			
		            </form>	
		        </div>
		        <div id="search-userName">
		            <form action="<%=request.getContextPath()%>/memberFinder">
		                <input type="hidden" name="searchType" value="userName"/>
		                <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."
		                    value="<%="userName".equals(searchType)?searchKeyword:""%>"/>
		                <button type="submit">검색</button>			
		            </form>	
		        </div>
		        <div id="search-gender">
		            <form action="<%=request.getContextPath()%>/memberFinder">
		                <input type="hidden" name="searchType" value="gender"/>
		                <input type="radio" name="searchKeyword" value="M" <%="gender".equals(searchType) && "M".equals(searchKeyword)?"checked":""%>> 남
		                <input type="radio" name="searchKeyword" value="F" <%="gender".equals(searchType) && "F".equals(searchKeyword)?"checked":""%>> 여
		                <button type="submit">검색</button>
		            </form>
		        </div>
    		</div>
    		<div id="numPerPage-container">
    		페이지당 회원수 :
    		<form name="numPerPageFrm" action="<%=request.getContextPath()%>/memberList">
    			<input type="hidden" name="cPage" value="<%=cPage%>">
    			<select name="numPerPage" id="numPerPage">
    				<option value="10" <%=numPerPage==10?"selected":"" %>>10</option>
    				<option value="5" <%=numPerPage==5?"selected":"" %>>5</option>
    				<option value="3" <%=numPerPage==3?"selected":"" %>>3</option>
    			</select>
    		</form>
    		</div>
    	</div>
           <table id="tbl-member">
            <thead>
                <tr>
                    <th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
	             </tr>
            </thead>
            <tbody>
            <%if(list==null||list.isEmpty()) { %>
            <tr>
            	<td colspan="9" align="center"> 검색결과가 없습니다.</td>
            </tr>
			<% } 
			else { 
				for(Member m : list)
				{			
			%>
			<tr>
				<td><a href="<%=request.getContextPath() %>/memberView?userId=<%=m.getUserId()%>"><%= m.getUserId() %></a></td>
				<td><%=m.getUserName()%></td>
                <td><%="M".equals(m.getGender())?"남":"여"%></td>
                <td><%=m.getAge()%></td>
                <td><%=m.getEmail()%></td>
                <td><%=m.getPhone()%></td>
                <td><%=m.getAddress()%></td>
                <td><%=m.getHobby()%></td>
                <td><%=m.getEnrollDate()%></td>	
			</tr>
			<% }
			} %>
			</tbody>
		</table>
		<div id='pageBar'>
			<%=pageBar %>
		</div>	
	</section>
<%@ include file="/views/common/footer.jsp"%>