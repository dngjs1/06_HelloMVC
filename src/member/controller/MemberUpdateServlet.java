package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name="MemberUpdateServlet",urlPatterns="/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getAttribute("msg"));
		String userId=request.getParameter("userId_");
		//String password=request.getParameter("password_");
		String userName=request.getParameter("userName");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String[] hobbys=request.getParameterValues("hobby");
		String hobby=String.join(",", hobbys);
		Member member=new Member(userId,"",userName,gender,age,email,phone,address,hobby,null);
		int result = new MemberService().updateMember(member);
		
		String view="";
		String msg="";
		String loc="";
		if(result>0) {
			msg="성공적으로 회원정보를 수정하였습니다.";
		}else {
			msg="회원정보 수정에 실패하였습니다.";
		}
		view="views/common/msg.jsp";
		loc="/";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
