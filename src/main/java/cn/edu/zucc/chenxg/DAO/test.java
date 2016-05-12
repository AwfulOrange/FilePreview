package cn.edu.zucc.chenxg.DAO;

import java.util.Date;
import java.util.List;

import org.json.JSONException;

import cn.edu.zucc.chenxg.model.Filepreview;
import cn.edu.zucc.chenxg.ws.WebServiceClient;

public class test {
	public static void main(String[] args) throws JSONException {
		Filepreview fp =new Filepreview();
//		fp.setSid(1);
		fp.setPreviewfilepath("111");
		fp.setLastpreviewtime(new Date());
		fp.setFilepath("filepath");
		FilePreviewDAO impl=new FilePreviewDAO();
		impl.createFile(fp);
//		impl.c
//		List<FilePreview> fps = impl.getFileByUser("1");
//		System.out.println(impl);
//		new WebServiceClient().getUserDefaultFile();
//		UserInfoDAO UserDao = new UserInfoDAO();
//		UserInfo user=UserDao.getUserInfoByUserID("1");
//		System.out.println(user.getUserName());
		webserviceTest();
	}
	
	public void filePreviewDaoTest(){
		
	}
	
	public static void webserviceTest() throws JSONException{
		List<Filepreview> fp = new WebServiceClient().getUserDefaultFile();
	}

}
