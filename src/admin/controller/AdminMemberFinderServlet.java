package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberFinderServlet() {
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
		String searchType=request.getParameter("searchType");
		String searchKeyword=request.getParameter("searchKeyword");
		
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(Exception e) {
			cPage=1;
		}
		int numPerPage;
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(Exception e) {
			numPerPage=10;
		}
		
		List<Member> list=null;
		switch(searchType) {
		case "userId" : list=new AdminService().selectMemberById(searchKeyword,cPage,numPerPage);break;
		case "userName" : list=new AdminService().selectMemberByName(searchKeyword,cPage,numPerPage);break;
		case "gender" : list=new AdminService().selectMemberByGender(searchKeyword,cPage,numPerPage);break;
		}
		
		int totalMember=0;
		switch(searchType) {
		case "userId" : totalMember=new AdminService().selectCountByUserId(searchKeyword);break;
		case "userName" : totalMember=new AdminService().selectCountByUserName(searchKeyword);break;
		case "gender" : totalMember=new AdminService().selectCountByGender(searchKeyword);break;
		}
		System.out.println(totalMember);
		
		int totalPage=(int)Math.ceil((double)totalMember/numPerPage);
		String pageBar="";
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/memberFinder?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/memberFinder?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/memberFinder?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		System.out.println(pageBar);
		request.setAttribute("list", list);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.setAttribute("cPage",cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/memberFinder.jsp");
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
