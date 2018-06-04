package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardAddEndServlet
 */
@WebServlet("/boardFormEnd")
public class BoardAddEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAddEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. mutilpart/form 제대로 들어왔는지 확인!
				//2. root폴더 하고 저장경로 설정
				//3. file최대크기 설정
				//4. MutipartRequest객체를 생성 ---> 파일 업로드
				//5. MutipartRequest객체 파라미터값을 가져오기
				//6. 비지니스 로직실행(service->dao)
				//7. view단 결과 위임
				
				RequestDispatcher rd=null;
				//1.작업실시!
				if(!ServletFileUpload.isMultipartContent(request))
				{
					request.setAttribute("msg", "게시판작성 오류[관리자에게 문의]");
					request.setAttribute("loc", "/");
					rd=request.getRequestDispatcher("/views/common/msg.jsp");
					rd.forward(request, response);
					return;
				}
				//2. 작업
				String root=getServletContext().getRealPath("/");
				String saveDir=root+"uploadfiles"+File.separator+"board";
				//3. 작업
				int maxSize=1024*1024*10;//10MB설정
				  
				
				//4. 작업
				MultipartRequest mpr=new MultipartRequest(
									request, 
									saveDir,
									maxSize,
									"UTF-8",
									new MyFileRenamePolicy());
				//5. 작업 데이터 삽입작업(비지니스~)
				Board b=new Board();
				b.setBoardTitle(mpr.getParameter("title"));
				b.setBoardWriter(mpr.getParameter("writer"));
				b.setBoardContent(mpr.getParameter("content"));
				b.setOriginalFileName(mpr.getOriginalFileName("up_file"));
				b.setRenameFileName(mpr.getFilesystemName("up_file"));
				
				int result = new BoardService().insertBoard(b);
				
				String view="/views/common/msg.jsp";
				String msg="";
				String loc="/boardList";
				if(result>0)
				{
					msg="게시글 등록성공!!";
				}
				else
				{
					msg="게시글 등록실패!!";
				}
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				rd=request.getRequestDispatcher(view);
				rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
