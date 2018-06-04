package common;

import java.io.File;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File oldFile) {
		File newFile=null;
		do {
			//현재시간을 가져오는 매소드
			long currentTime=System.currentTimeMillis();
			//날짜형식으로 밀리세컨까지 표시
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			//0~999까지의 임의의 수 발생!
			int rndNum=(int)(Math.random()*1000);
			//확장자명 가져오기
			String oldName=oldFile.getName();
			String ext="";
			int dot=oldName.indexOf(".");
			if(dot>-1)
			{
				ext=oldName.substring(dot);
			}
			String newName=sdf.format(currentTime)+"_"+rndNum+ext;
			newFile=new File(oldFile.getParent(),newName);
			
		}while(!createFile(newFile));
		
		return newFile;
	}
	
	public boolean createFile(File newFile) {
		try {
			//파일을 생성하는 메소드, 만약에 생성하는곳에 파일이 있으면 생성하지 않고,
			//exception발생, false/ 없으면 true 리턴
			return newFile.createNewFile();
		}catch(Exception e) {
			return false;
		}
	}

	
}
