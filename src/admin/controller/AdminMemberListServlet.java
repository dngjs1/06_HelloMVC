package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member memberLoggedId=(Member)request.getSession().getAttribute("memberLoggedIn");
		//관리자가 아닐경우 페이지 접속을 차단
		if(memberLoggedId==null || !"admin".equals(memberLoggedId.getUserId())){
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		//페이징 처리
		//현재페이지, 페이지에 표시될 자료수를 초기화.
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e){
			cPage=1;
		}
		int numPerPage;
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e){
			numPerPage=10;
		}
		//관리자용 리스트를 받아오는 로직을 처리
		//현재 페이지에 표시될 자료만 필터하여 가져오는 로직 구성
		ArrayList<Member> list = new AdminService().selectMemberList(cPage,numPerPage);
		//페이지 구성 pageBar작성
		int totalMember=new AdminService().selectCount();
		int totalPage=(int)Math.ceil((double)totalMember/numPerPage);
		String pageBar="";
		int pageBarSize=5;
		//cPage 1~5사이의 숫자 : 1
		//cPage 6~10사이의 숫자 : 6
		int pageNo=  ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		//페이지바에 들어갈 html구문 작성
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/memberList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		//중간에 들어가는 페이지 버튼(1~5/6~10 ...)만들기
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		//[다음]페이지 만들기
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		RequestDispatcher rd=request.getRequestDispatcher("/views/admin/memberList.jsp");
		rd.forward(request, response);
		//응답화면을 작성
		
		/*request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/views/admin/memberList.jsp");
		rd.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
