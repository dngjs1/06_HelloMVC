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
 * Servlet implementation class MemberEnrollEndServlet
 */
@WebServlet(name="MemberEnrollEndServlet",urlPatterns="/memberEnrollEnd")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId=request.getParameter("userId_");
		String password=request.getParameter("password_");
		String userName=request.getParameter("userName");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String[] hobbys=request.getParameterValues("hobby");
		String hobby=String.join(",", hobbys);
		/*for(int i=0;i<hobbys.length;i++) {
		 * if(i==hobbys.length-1){
		 * 	hobby+=hobbys[i];
		 * }
			hobby+=hobbys[i]+",";
		}*/
		Member member=new Member(userId,password,userName,gender,age,email,phone,address,hobby,null);
		System.out.println(member);
		int result=new MemberService().insertMember(member);
		String msg="";
		String loc="/";
		String view="views/common/msg.jsp";
		if(result>0) {
			System.out.println("회원가입 성공");
			msg="회원가입에 성공하셨습니다.";
		}else {
			System.out.println("회원가입 실패");
			msg="회원가입에 실패하셨습니다.";
		}
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
