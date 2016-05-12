package cn.edu.zucc.chenxg.preview;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.UUID;

import org.apache.xpath.axes.PathComponent;
import org.docx4j.Docx4J;
import org.docx4j.fonts.BestMatchingMapper;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.RFonts;

import cn.edu.zucc.chenxg.utils.Constant;

public class Docx2PDFConverter extends FileConverter {

	@Override
	public void convert(String inFile, String outPath) throws Exception {

		// Mac
		String regex = ".*(Courier New|Arial|Times New Roman|Comic Sans|Georgia|Impact|Lucida Console|Lucida Sans Unicode|Palatino Linotype|Tahoma|Trebuchet|Verdana|Symbol|Webdings|Wingdings|MS Sans Serif|MS Serif).*";
		
		PhysicalFonts.setRegex(regex);

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(inFile));

		Mapper fontMapper = new IdentityPlusMapper();
		wordMLPackage.setFontMapper(fontMapper);

		PhysicalFont font = PhysicalFonts.get("Arial Unicode MS");
		if (font != null) {
			fontMapper.put("Times New Roman", font);
			fontMapper.put("Arial", font);
		}
		fontMapper.put("Libian SC Regular", PhysicalFonts.get("SimSun"));

		Docx4J.toPDF(wordMLPackage, new FileOutputStream(new File(outPath)));

	}

}
