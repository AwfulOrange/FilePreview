package cn.edu.zucc.chenxg.preview;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;

import cn.edu.zucc.chenxg.utils.Constant;
import cn.edu.zucc.chenxg.utils.DeleteFiles;
import cn.edu.zucc.chenxg.utils.PathGenerater;
import cn.edu.zucc.chenxg.utils.WriteFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.List;

public class Xls2HtmlConverter extends FileConverter {
	
	public void convert(String inFile, String outPath)
			throws Exception {
		
		String HTMLPath = new PathGenerater().generateHTMLOutPath();

		HSSFWorkbook excelDocumet = new HSSFWorkbook(new FileInputStream(
				inFile));

		ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());


		excelToHtmlConverter.processWorkbook(excelDocumet);
		
		//excel's pictures not supported yet


		Document htmlDocument = excelToHtmlConverter.getDocument();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(out);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
		serializer.transform(domSource, streamResult);
		out.close();
		new WriteFile().writeFile(new String(out.toByteArray()), HTMLPath);
		new Html2PDFConverter().convertHtmlToPdf(HTMLPath, outPath);//conver html files to pdf
		new DeleteFiles().deleteFile(HTMLPath);
		System.out.println("1.xls");
		//delete html file
	}

	
}
