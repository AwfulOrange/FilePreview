package cn.edu.zucc.chenxg.context;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.edu.zucc.chenxg.DAO.FilePreviewDAO;
import cn.edu.zucc.chenxg.preview.Doc2HtmlConverter;
import cn.edu.zucc.chenxg.preview.Docx2PDFConverter;
import cn.edu.zucc.chenxg.preview.PPT2PDFConverter;
import cn.edu.zucc.chenxg.preview.PPTX2PDFConverter;
import cn.edu.zucc.chenxg.preview.Xls2HtmlConverter;
import cn.edu.zucc.chenxg.utils.Constant;
import cn.edu.zucc.chenxg.utils.PathGenerater;


public class Context {

	public String getPreviewPath(String inPath) throws Exception {
		
		return inPath;
	}

}
