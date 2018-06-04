package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet(name="MemberLoginServlet",
			urlPatterns="/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MemberLoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		int result=new MemberService().loginCheck(userId,password);
		String view="";
		if(result==MemberService.LOGIN_OK) {
			Member member=new MemberService().selectOne(userId);
			//System.out.println(member);
			
			//세션타임아웃을 정하는방법
			//1. session.setMaxInactiveInterval();(시간단위sec)
			//2. web.xml 에서 설정
			//<session-config>
				//<session-timeout>OO(시간단위min,정수만 가능)</session-timeout>
			//</session-config>
			//설정
			//1)application
			//2)server(tomcat)
			
			//session 객체를 받아서 login 데이터를 삽입
			HttpSession session=request.getSession();
			System.out.println(session.getId());
			session.setAttribute("memberLoggedIn",member);
			//session.setMaxInactiveInterval(30);//세션타임아웃
			//아이디 저장 쿠키 남기기
			String saveId=request.getParameter("saveId");
			Cookie c=new Cookie("saveId",userId);
			if(saveId!=null) {
				c.setMaxAge(6*24*60*60);//6일간 쿠키보관 (초단위)
				c.setPath("/");
				response.addCookie(c);
			}else {
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
			
			//request.setAttribute("memberLoggedIn", member);
			view="/";
		}else {
			String msg;
			if(result==MemberService.WRONG_PASSWORD) {
				msg="패스워드를 잘못 입력하셨습니다.";
			}else {
				msg="존재하지 않는 아이디 입니다.";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", "/");
			view="views/common/msg.jsp";
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
