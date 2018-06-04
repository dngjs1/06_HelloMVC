package notice.controller;

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

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/noticeupdateEnd")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		if(!ServletFileUpload.isMultipartContent(request)) { 
			rd=request.getRequestDispatcher("views/common/msg");
			request.setAttribute("msg", "공지사항작성오류[form:enctype 관리자에게 문의하세요");
			request.setAttribute("loc", "/");
		}
		//파일 업로드 시작!!
		//1. 파일 업로드 위치를 설정(직접경로)하고 폴더를 먼저 생성.
		String root=getServletContext().getRealPath("/");
		//파일시스템의 경로'\'와 웹에서의 경로'/'를 호환해줌.
		String saveDir=root+"uploadfiles"+File.separator+"notice";
		//2. 파일이 업로드 될 최대크기 설정
		int fileMax=1024*1024*10;//10MB
		//3. MutiPartRequest객체를 생성하여 파일 업로드
		//인자값 : request, 파일저장경로, 파일 최대 사이즈, 인코딩방식, 파일중복처리객체
		MultipartRequest mpr=new MultipartRequest(request, saveDir,fileMax
									,"UTF-8",new DefaultFileRenamePolicy());
		int no=Integer.parseInt(mpr.getParameter("no"));
		String noticeTitle=mpr.getParameter("title");
		String noticeWriter=mpr.getParameter("writer");
		String noticeContent=mpr.getParameter("content");
		String filePath=mpr.getFilesystemName("up_file");
		//이전 파일 삭제
		String oldFile=mpr.getParameter("old_file");
		
		File f=mpr.getFile("up_file");
		
		if(f!=null&&f.length()>0) { //이전파일을 삭제 해야함
			File deleteFile=new File(saveDir+"/"+oldFile);
			boolean flag=deleteFile.delete();
		}else {
			filePath=oldFile;
			if(filePath.equals("null")) {
				filePath=null;
			}
		}
		
		Notice n=new Notice();
		n.setNoticeNo(no);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeWriter(noticeWriter);
		n.setNoticeContent(noticeContent);
		n.setFilePath(filePath);
		int result=new NoticeService().updateNotice(n);
		
		String loc="/noticeList";
		String msg="";
		if(result>0) {
			msg="공지사항 수정 성공";
		}else {
			msg="공지사항 수정 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
