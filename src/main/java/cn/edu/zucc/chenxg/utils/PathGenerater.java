package cn.edu.zucc.chenxg.utils;

import java.util.UUID;

public class PathGenerater {
	// public static void main(String[] args) {
	// UUID uuid = UUID.randomUUID();
	// System.out.println(uuid);
	// }

	public String generatePDFOutPath() {
		return Constant.DEFUALT_PDF_PATH + UUID.randomUUID() + ".pdf";
	}

	public String generateHTMLOutPath() {
		return Constant.DEFUALT_HTML_PATH + UUID.randomUUID() + ".html";
	}

}
