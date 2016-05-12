package cn.edu.zucc.chenxg.preview;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.docx4j.openpackaging.exceptions.Docx4JException;

public abstract class FileConverter {
	
	public abstract void convert(String inFile,String outPath) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException, Docx4JException, Exception;
	
	

}
