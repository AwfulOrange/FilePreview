package cn.edu.zucc.chenxg.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class ChineseSolver {
	public static void main(String[] args) {
		System.out.println("helloword");
	}

	public void extractCss(String inputfile) throws IOException { // 将字体全部修改后重新存回原来的HTML文件之中

		File htmlfile = new File(inputfile);
		org.jsoup.nodes.Document doc = Jsoup.parse(htmlfile, "UTF-8", "");//use Jsoup to make HTML file Operable.                // 参数用于解决文件中URLs是相对路径的问题。如果不需要可以传入一个空的字符串
		Elements style = doc.getElementsByTag("style");
		String css = style.get(0).data();// 获取style标签中的数据
		String newcss = null;

		String[] slist = css.split(";");
		for (int i = 0; i < slist.length; i++) {//replace font family for Chinese issues
			if (slist[i].contains("font-family")) {

				slist[i] = "font-family:\"Arial Unicode MS\"";

			}
			newcss += slist[i] + ";";
		}
		style.get(0).empty();
		style.get(0).append(newcss);
		String content = new html2xhtml().parseXhtml(doc.html());//html->xhtml
		if (htmlfile.exists()) {//recreate file
			htmlfile.delete();
			htmlfile.createNewFile();
		}
		BufferedWriter bw = null;
		bw = new BufferedWriter(new FileWriter(htmlfile, true));

		bw.write(content);
		bw.close();


	}

}
