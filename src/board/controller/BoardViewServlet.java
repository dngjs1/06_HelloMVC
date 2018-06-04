package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		Board b= new BoardService().selectOne(no);
		List<BoardComment> list=null;
		if(b!=null) {
			list=new BoardService().selectcomment(no);
		}
		Cookie[] cookie=request.getCookies();
		String boardCookieVal="";
		boolean hasRead=false;
		//사이트방문시 아무런 쿠키를 갖고있지 않으면 쿠키값은 null이 나옴.
		if(cookie!=null) {
			outter:
				for(Cookie c:cookie) {
					String name=c.getName();
					String value=c.getValue();
					
					if("boardCookie".equals(name)) {
						boardCookieVal=value;
						if(value.contains("|"+no+"|")) {  //문자열이 있으면 true.
							hasRead=true;
							break outter;
						}
					}
				}
		}
		
		if(!hasRead) {
			new BoardService().incrementCount(no);
			
			Cookie c=new Cookie("boardCookie", boardCookieVal+"|"+no+"|");
			c.setMaxAge(-1); //브라우저를 닫는경우 삭제.
			response.addCookie(c);
		}
		request.setAttribute("comment",list);
		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
