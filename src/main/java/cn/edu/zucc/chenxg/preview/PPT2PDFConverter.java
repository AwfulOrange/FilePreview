package cn.edu.zucc.chenxg.preview;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.xslf.util.PPTX2PNG;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import cn.edu.zucc.chenxg.utils.DeleteFiles;
import cn.edu.zucc.chenxg.utils.PNG2PDF;
import cn.edu.zucc.chenxg.utils.Constant;

public class PPT2PDFConverter extends FileConverter {

	public void convert(String inFile, String outPath) throws IOException {
		String imagePath = new Constant().DEFUALT_IMG_PATH;
		FileInputStream is = new FileInputStream(inFile);
		HSLFSlideShow ppt = new HSLFSlideShow(is);
		Dimension pgsize = ppt.getPageSize();

		is.close();

		int idx = 0;

		for (int i = 0; i < ppt.getSlides().size(); i++) {
			for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
				if (shape instanceof HSLFTextShape) {
					HSLFTextShape txtshape = (HSLFTextShape) shape;

					for (HSLFTextParagraph textPara : txtshape.getTextParagraphs()) {
						List<HSLFTextRun> textRunList = textPara.getTextRuns();
						for (HSLFTextRun textRun : textRunList) {
							textRun.setFontFamily("Arial Unicode MS");
						}
					}
				}
			}
		}
		
		

		for (HSLFSlide slide : ppt.getSlides()) {

			BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			// clear the drawing area
			// graphics.setPaint(Color.white);
			graphics.setPaint(ppt.getSlides().get(idx).getBackground().getFill().getForegroundColor());

			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

			// render
			slide.draw(graphics);

			// save the output
			FileOutputStream out = new FileOutputStream(imagePath + "slides" + "-" + idx + ".png");
			javax.imageio.ImageIO.write(img, "png", out);
			out.close();

			idx++;
		}

		new PNG2PDF().pngsToPDF(idx, outPath);
		new DeleteFiles().deletePNGs(idx);

	}




}