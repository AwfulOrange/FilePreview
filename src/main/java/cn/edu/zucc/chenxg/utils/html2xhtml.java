package cn.edu.zucc.chenxg.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.w3c.tidy.Tidy;

public class html2xhtml {
	public String parseXhtml(String f_in){

	      ByteArrayInputStream stream = new ByteArrayInputStream(f_in.getBytes());

	      ByteArrayOutputStream  tidyOutStream = new ByteArrayOutputStream();
	      
	      Tidy tidy = new Tidy();//实例化Tidy对象
	     
	      tidy.setInputEncoding("UTF-8"); //设置输入
	      
	      tidy.setQuiet(true);//如果是true  不输出注释，警告和错误信息
	     
	      tidy.setOutputEncoding("UTF-8"); //设置输出
	    
	      tidy.setShowWarnings(false);//不显示警告信息
	      
	      tidy.setIndentContent(true);//缩进适当的标签内容。
	  
	      tidy.setSmartIndent(true);    //内容缩进
	      tidy.setIndentAttributes(false);
	     
	      tidy.setPrintBodyOnly(false); //只输出body内部的内容
	      
	      tidy.setWraplen(1024);//多长换行
	     
	      tidy.setXHTML(true); //输出为xhtml
	     
	      tidy.setMakeClean(true); //去掉没用的标签
	     
	      tidy.setWord2000(true); //清洗word2000的内容
	    
	      tidy.setErrout(new PrintWriter(System.out));  //设置错误输出信息
	      tidy.parse(stream, tidyOutStream);
	      return tidyOutStream.toString();
	}

}
