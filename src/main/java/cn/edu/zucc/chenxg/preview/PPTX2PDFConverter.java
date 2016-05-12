package cn.edu.zucc.chenxg.preview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import cn.edu.zucc.chenxg.utils.DeleteFiles;
import cn.edu.zucc.chenxg.utils.PNG2PDF;
import cn.edu.zucc.chenxg.utils.Constant;

public class PPTX2PDFConverter extends FileConverter {

	@Override
	public void convert(String inFile, String outPath) throws IOException {
		String imagePath = new Constant().DEFUALT_IMG_PATH;
		FileInputStream is = new FileInputStream(inFile);
		@SuppressWarnings("resource")
		XMLSlideShow pptx = new XMLSlideShow(is);
		Dimension pgsize = pptx.getPageSize();
		
		
		is.close();
		int idx = 0;

		// correct the font
		for (int i = 0; i < pptx.getSlides().size(); i++) {
			for (XSLFShape shape : pptx.getSlides().get(i).getShapes()) {
				if (shape instanceof XSLFTextShape) {
					XSLFTextShape txtshape = (XSLFTextShape) shape;
//					System.out.println("txtshape" + (i + 1) + ":" + txtshape.getShapeName());
//					System.out.println("text:" + txtshape.getText());

					for (XSLFTextParagraph textPara : txtshape.getTextParagraphs()) {
						List<XSLFTextRun> textRunList = textPara.getTextRuns();
						for (XSLFTextRun textRun : textRunList) {
							textRun.setFontFamily("Arial Unicode MS");
						}
					}
				}
			}
		}

		/***** or convert the slides to images ****/

		for (XSLFSlide slide : pptx.getSlides()) {
			BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			// clear the drawing area
			graphics.setPaint(Color.WHITE);

			// graphics.setPaint();
			graphics.setBackground(slide.getBackground().getFillColor());
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

			// render
			slide.draw(graphics);

			// save the output
			FileOutputStream out = new FileOutputStream(imagePath + "slides-" + idx + ".png");
			javax.imageio.ImageIO.write(img, "png", out);
			out.close();

			idx++;
		}
		new PNG2PDF().pngsToPDF(idx, outPath);
		new DeleteFiles().deletePNGs(idx);

	}

}
