package cn.edu.zucc.chenxg.context;

import cn.edu.zucc.chenxg.preview.FileConverter;

public class FileContext {
	
	private FileConverter fc;
	
	public FileContext(FileConverter fileConverter){
		this.fc = fileConverter;
	}
	
	public void execute(String inFile,String outPath) throws Exception{
		fc.convert(inFile, outPath);
	}
	

}

