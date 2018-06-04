package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardRef=Integer.parseInt(request.getParameter("boardRef"));
		String boardwriter=request.getParameter("boardCommentWriter");
		String boardContent=request.getParameter("boardCommentContent");
		int boardlevel=Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef=Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment bc=new BoardComment();
		bc.setBoardRef(boardRef);
		bc.setBoardCommentWriter(boardwriter);
		bc.setBoardCommentContent(boardContent);
		bc.setBoardCommentLevel(boardlevel);
		bc.setBoardCommentRef(boardCommentRef);
		
		int result=new BoardService().insertComment(bc);
		
		String view="/views/common/msg.jsp";
		String msg="";
		String loc="/boardView?no="+boardRef;
		if(result>0)
		{
			msg="댓글 등록 성공";
		}
		else
		{
			msg="댓글 등록 실패";
		}
		
		request.setAttribute("msg",msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

