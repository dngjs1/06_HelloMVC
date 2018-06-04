package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/memberView")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId"); //jsp문의 ?뒤쪽의 키값으로 받아옴
		System.out.println(userId);
		Member member=new MemberService().selectOne(userId);
		String msg="";
		String view="";
		String loc="";
		if(member==null) {
			msg="회원정보를 가져올 수 없습니다.";
			view="views/common/msg.jsp";
			loc="/";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}else {
			view="views/member/memberView.jsp";
			request.setAttribute("member", member);
		}
		RequestDispatcher rd=request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
