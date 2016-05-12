package cn.edu.zucc.chenxg.preview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

import cn.edu.zucc.chenxg.utils.ChineseSolver;
import cn.edu.zucc.chenxg.utils.Constant;


public class Html2PDFConverter {

	public boolean convertHtmlToPdf(String inFile, String outPath)
			throws Exception {
		
		//数据库相关操作 将这两个文件对应起来

		OutputStream os = new FileOutputStream(outPath);
		ITextRenderer renderer = new ITextRenderer();
		new ChineseSolver().extractCss(inFile);
		String url = new File(inFile).toURI().toURL().toString();
		
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont(Constant.DEFUALT_FONT_PATH, BaseFont.IDENTITY_H,
				BaseFont.NOT_EMBEDDED);
		
		renderer.setDocument(url);
		renderer.getSharedContext().setBaseURL("file:"+Constant.DEFUALT_HTML_IMG_PATH);// 获取Html文件中的图片
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return true;
	}

}
