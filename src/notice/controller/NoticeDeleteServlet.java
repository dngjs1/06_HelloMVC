package notice.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/noticeDelete")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no=Integer.parseInt(request.getParameter("no"));
		int result = new NoticeService().deleteNotice(no);
		String filePath=request.getParameter("filePath");
		
		String saveDir=getServletContext().getRealPath("/uploadfiles/notice");
		
		String msg="";
		if(result>0) {
			if(!filePath.equals("null")) {
				File deleteFile=new File(saveDir+"/"+filePath);
				boolean flag=deleteFile.delete();
			}
			msg="공지 삭제를 성공적으로 완료하였습니다.";
		}else {
			msg="공지 삭제에 실패했습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/noticeList");
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
