package board.controller;

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
 * Servlet implementation class BoardFileDownLoadServlet
 */
@WebServlet("/boardFileDownLoad")
public class BoardFileDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rName=request.getParameter("rName");
		String oName=request.getParameter("oName");
		//파일 입출력과 동일한 방식으로 처리
		//inputStream(server)/outputStream(client)
		//1.root폴더 구하기 파일이 저장되어 있는 root
		String saveDir=getServletContext().getRealPath("/uploadfiles/board");
		File downFile=new File(saveDir+"/"+rName);
		//입력스트림 만들기
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(downFile));
		//아웃스트림
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		//파일 인코딩작업(브라우저 별)
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1
				||request.getHeader("user-agent").indexOf("Trident")!=-1;
		String resFile="";
		if(isMSIE) {
			resFile=URLEncoder.encode(oName,"UTF-8");
			resFile=resFile.replaceAll("\\+", "%20");
		}else {
			resFile=new String(oName.getBytes("UTF-8"),"ISO-8859-1");//8859_1
		}
		//응답해더 설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFile);
		//파일 브라우저에 쓰기
		int read=-1;
		while((read=bis.read())!=-1) {
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
