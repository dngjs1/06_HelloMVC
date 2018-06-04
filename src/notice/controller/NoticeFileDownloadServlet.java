package notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownloadServlet
 */
@WebServlet("/noticeFileDownload")
public class NoticeFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir=getServletContext().getRealPath("/uploadfiles/notice");
		String filename=request.getParameter("filePath");
		//해당하는 파일을 불러오기
		File downFile=new File(saveDir+"/"+filename);
		//파일을 램과 연결하는 스트림 생성, 서버의 램으로 파일을 옮김.
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(downFile));
		//client의 컴퓨터와 스트림 연결
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		//브라우저에서 받을 수 있게 분기 처리
		//1.브라우저 확인
		//인코딩을 UTF-8형식으로 바꿔주는것 한글명 파일 깨짐 방지.
		String resFile="";
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1 //indexOf는 값이없으면 -1반환
				|| request.getHeader("user-agent").indexOf("Trident")!=-1; //마이크로소프트 익스플로러
		
		if(isMSIE) { //브라우저가 익스플로러일때.
			resFile=URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20");
		}else {
			//utf-8로 인코딩하고 다시한번 뒤에있는걸로 바꿔줘야 한글로 나옴.
			resFile=new String(filename.getBytes("UTF-8"),"8859_1");
		}
		//파일 전송 설정 response header를 설정, 이렇게 설정해줘야 파일이 넘어감.
		response.setContentType("application/octet-stream");
		response.setHeader("content-Disposition","attachment;filename="+resFile);
		//attachment를 inline으로 바꿔주면 브라우저에서 오픈(가능한것만)
		//전송파일을 client에 써주기
		int read=-1;
		while((read=bis.read())!=-1)
		{
			bos.write(read);
		}
		bis.close();
		bos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
