package cn.edu.zucc.chenxg.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class PNG2PDF {

	public void pngsToPDF(int count, String outPath) {
		try {
			int id = 0;
			String imageBufferPath = new Constant().DEFUALT_IMG_PATH;
			String imagePath = null;
			Document doc = new Document(null, 0, 0, 0, 0);
			FileOutputStream fos = new FileOutputStream(outPath);
			PdfWriter.getInstance(doc, fos);

			List<BufferedImage> imgs = new ArrayList<BufferedImage>();
			List<Image> Images = new ArrayList<Image>();
			for (id = 0; id < count; id++) {
				imagePath = imageBufferPath + "slides" + "-" + id + ".png";
				imgs.add(id , ImageIO.read(new File(imagePath)));
				Images.add(id, Image.getInstance(imagePath));

			}

			doc.setPageSize(new Rectangle(imgs.get(0).getWidth(), imgs.get(0).getHeight()));
			doc.open();
			for (id = 1; id <= count; id++) {
				doc.add(Images.get(id - 1));
				doc.newPage();
			}
			doc.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
