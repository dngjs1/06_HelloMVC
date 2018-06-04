<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="board.model.vo.Board,java.util.*,board.model.vo.BoardComment" %>
    <%
        Board b = (Board)request.getAttribute("board");
    	List<BoardComment> commentList=(List)request.getAttribute("comment");
    %>
<%@ include file="/views/common/header.jsp"%>

    <style>
    div#board-container{width:600px; margin:0 auto; text-align:center;}
    div#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
     /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    </style>

		<div id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%=b.getBoardNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getBoardCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				<% if(b.getOriginalFileName() != null){ %>
				<a href="javascript:fn_fileDownload('<%= b.getOriginalFileName() %>','<%=b.getRenameFileName() %>');">
					<img alt="첨부파일" src="<%=request.getContextPath() %>/image/file.png" width=16px>
				<%= b.getOriginalFileName() %>
				</a>
				<% }%>
					<script>
						function fn_fileDownload(oriName, reName)
						{
							var url="<%=request.getContextPath()%>/boardFileDownLoad";
							oName=encodeURIComponent(oriName);
							location.href=url+"?oName="+oName+"&rName="+reName;
						}
					</script>
				
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%=b.getBoardContent()%></td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			
			<tr>
				<th colspan="2">
					<input type="button" value="목록으로" onclick="fn_goList()">
					<script>
						function fn_goList()
						{
							location.href="<%=request.getContextPath()%>/boardList";
						}
					</script>
				<% if(memberLoggedIn!=null && 
				(b.getBoardWriter().equals(memberLoggedIn.getUserId())
				|| "admin".equals(memberLoggedIn.getUserId()))){ %>
					<input type="button" value="수정하기" onclick="fn_updateBoard()">
					<input type="button" value="삭제하기" onclick="fn_deleteBoard()">
				<%} %>
				</th>
			</tr>
		</table>
			<% if(memberLoggedIn!=null && 
            (b.getBoardWriter().equals(memberLoggedIn.getUserId())
            || "admin".equals(memberLoggedIn.getUserId())) ){ %>
		    <form name="boardDelFrm" action="<%=request.getContextPath()%>/boardDelete" method="post">
		    <input type="hidden" name="no" value="<%=b.getBoardNo() %>" />
		    <input type="hidden" name="renamedFileName" 
		    value="<%= b.getRenameFileName()!=null?b.getRenameFileName():""%>" />
		    </form>
		    <script>
		    function fn_updateBoard(){
		        location.href="<%=request.getContextPath()%>/boardUpdate?no=<%=b.getBoardNo() %>";
		    }
		    function fn_deleteBoard(){
		        if(!confirm('이 게시글을 정말 삭제하시겠습니까?')) 
		        	return;
		        $("[name=boardDelFrm]").submit();
		    }
		    </script>
		    <%} %>
		<div id="comment-container">
        <%-- text-area 포커스, 등록버튼을 누른 경우 로그인여부를 검사해서 경고창을 띄어줌. --%>
        <div class="comment-editor">
            <form name='boardCommentFrm' action="<%=request.getContextPath()%>/boardCommentInsert" method="post">
                <input type="hidden" name="boardRef" value="<%=b.getBoardNo() %>" />
                <input type="hidden" name="boardCommentWriter" value="<%=memberLoggedIn!=null?memberLoggedIn.getUserId():"" %>" />
                <input type="hidden" name="boardCommentLevel" value="1" />
                <input type="hidden" name="boardCommentRef" value="0" /> <!-- 댓글인 경우  참조댓글이 없으므로 0으로 처리함. -->
                <textarea name="boardCommentContent" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-insert">등록</button>
            </form>
            <script>
            	$(function(){
            		$("[name=boardCommentContent]").focus(function(e){
            			if(<%=memberLoggedIn==null%>){
            				fn_loginAlert();
            				e.preventDefault();
            				return;
            			}
            		});
            		$("[name=boardCommentFrm]").submit(function(e){
            			if(<%=memberLoggedIn==null%>)
            			{
            				fn_loginAlert();
            				e.preventDefault();
            				return;
            			}
            			var len=$("textarea[name=boardComment]").val().trim().length;
            			if(len==0)
            			{	
            				e.preventDefault();	
            			}
            		});
            	});
            	function fn_loginAlert()
            	{
            		alert("로그인 후 이용할 수 있습니다.");
            		$("#userId").focus();
            	};
            </script>
        </div>
        <table id='tbl-comment'>
			<% if(commentList!=null) { 
				for(BoardComment bc:commentList)
				{ 
				if(bc.getBoardCommentLevel()==1) {%>        	
        		<tr class='level1'>
        			<td> 
        				<sub class='comment-writer'><%=bc.getBoardCommentWriter()%></sub>
        				<sub class='comment-date'><%=bc.getBoardCommentDate()%></sub>
        				<br/>
        				<%=bc.getBoardCommentContent() %>
        			</td>
        	        			
        			<td>
        				<button class='btn-reply' value="<%=bc.getBoardCommentNo() %>">답글</button>
        				<button class='btn-delete' value="<%=bc.getBoardCommentNo() %>">삭제</button>
        			</td>
        		</tr>
        	<% } 
				else 
				{ %>
					<tr class='level2'>
						<td>
							<sub><%=bc.getBoardCommentWriter()%></sub>
							<sub><%=bc.getBoardCommentDate()%></sub>
							<br>
							<%=bc.getBoardCommentContent() %>
						</td>
						<td>
							<button class='btn-delete' value="<%=bc.getBoardCommentNo() %>">삭제</button>
						</td>
					</tr>
			<% }
			}
        } %>
        	<script>
					$(".btn-reply").on('click',function(e){
						<% if(memberLoggedIn!=null) { %>
							//화면에 출력될 답글 입력창 만들기
							var tr=$("<tr></tr>");//태그생성
							var html="<td style='display:none;text-align:left;'colspan=2>";
							html+='<form action="<%=request.getContextPath()%>/boardCommentInsert" method="post">';
							html+="<input type='hidden' name='boardRef' value='<%=b.getBoardNo()%>'/>";
							html+="<input type='hidden' name='boardCommentWriter' value='<%=memberLoggedIn.getUserId()%>'/>";
							html+="<input type='hidden' name='boardCommentLevel' value='2'/>";
							html+="<input type='hidden' name='boardCommentRef' value='"+$(this).val()+"'/>";
							html+="<textarea name='boardCommentContent' cols='60' rows='1'></textarea>";
							html+="<button type='submit' class='btn-insert2'>등록</button>";
							html+="</form></td>";   							
							//위에서 작성한 html구문을 tr변수 text노드에 삽입
							tr.html(html);
							//작성된 tr태그(객체)를 원본 html구문의 
							//(tr class=leve1)뒤에 삽입
							tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
							//이벤트가 1회만 발생하게 제한
							$(this).off('click');
							//답글달고 버튼을 누르면 해당 서블릿에 데이터전송
							tr.find('form').submit(function(e){
								if(<%=memberLoggedIn==null%>)
								{
									fn_loginAlert();
									e.preventDefault();
									return;
								}
								var len=$(this).children('textarea').val().trim().length;
								
								if(len==0)
								{
									e.preventDefault();
								}
							});
					
						<% } %>		
					});
				</script>
        </table>
    </div>
    </div>
<%@ include file="/views/common/footer.jsp"%>
