package cn.edu.zucc.chenxg.preview;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import cn.edu.zucc.chenxg.utils.Constant;
import cn.edu.zucc.chenxg.utils.DeleteFiles;
import cn.edu.zucc.chenxg.utils.PathGenerater;
import cn.edu.zucc.chenxg.utils.WriteFile;

public class Doc2HtmlConverter extends FileConverter {

	public void convert(String inFile, String outPath) throws Exception {

		String HTMLPath = new PathGenerater().generateHTMLOutPath();
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(inFile));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
					float heightInches) {
				return suggestedName;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		// save pictures
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				// System.out.println(i);
				try {
					pic.writeImageContent(
							new FileOutputStream(Constant.DEFUALT_HTML_IMG_PATH + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();

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
		new Html2PDFConverter().convertHtmlToPdf(HTMLPath, outPath);
		new DeleteFiles().deleteFile(HTMLPath);

		// delete related pictures
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				new DeleteFiles().deleteFile(Constant.DEFUALT_HTML_IMG_PATH + pic.suggestFullFileName());
			}
		}

	}

}
