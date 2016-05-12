package cn.edu.zucc.chenxg.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.zucc.chenxg.DAO.FilePreviewDAO;
import cn.edu.zucc.chenxg.DAO.FilePreviewDAO;
import cn.edu.zucc.chenxg.context.Context;
import cn.edu.zucc.chenxg.context.FileContext;
import cn.edu.zucc.chenxg.model.Filepreview;
import cn.edu.zucc.chenxg.preview.Doc2HtmlConverter;
import cn.edu.zucc.chenxg.preview.Docx2PDFConverter;
import cn.edu.zucc.chenxg.preview.PPT2PDFConverter;
import cn.edu.zucc.chenxg.preview.PPTX2PDFConverter;
import cn.edu.zucc.chenxg.preview.Xls2HtmlConverter;
import cn.edu.zucc.chenxg.utils.Constant;
import cn.edu.zucc.chenxg.utils.JSONConstructer;
import cn.edu.zucc.chenxg.utils.PathGenerater;

@Path("/Preview")
public class WebServiceServer {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("Path/{inPath}")
	public String sayHello(@PathParam("inPath") String inPath) throws Exception {
		return inPath;
		// return new Context().getPreviewPath(inPath);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("User/{userID}")
	public String getAllFileByUser(@PathParam("userID") String userID) throws Exception {
		// return new Context().getPreviewPath(inPath);

		String result = "";
		JSONArray jsonArray = new JSONArray();
		FilePreviewDAO previewDao = new FilePreviewDAO();
		List<Filepreview> files = previewDao.getFileByUser(userID);

		if (files.size() > 0) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("result", true);
				jsonArray.put(obj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for (Filepreview c : files) {
				jsonArray.put(JSONConstructer.constructJSON(c));
			}
			result = jsonArray.toString();
		} else {
			result = JSONConstructer.constructErrorJSON("userfile", false, "NO aviliable data.").toString();
		}
		return result;

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("Convert/{SID}")
	public String getPreviewPath(@PathParam("SID") int SID) throws Exception {
		String outPath = new PathGenerater().generatePDFOutPath();

		FilePreviewDAO impl = new FilePreviewDAO();
		if (impl.isPreviewExists(SID) != null) {
			Filepreview fp = impl.isPreviewExists(SID).get(0);
			if (fp.getPreviewfilepath() == null) {

				FileContext filesuper = null;
				String[] names = fp.getFilepath().split("\\.");

				String filetype = names[names.length - 1];
				switch (filetype) {
				case Constant.DOC_SUFFIX:
					filesuper = new FileContext(new Doc2HtmlConverter());
					break;

				case Constant.DOCX_SUFFIX:
					filesuper = new FileContext(new Docx2PDFConverter());
					break;
				case Constant.EXCEL_SUFFIX:
					filesuper = new FileContext(new Xls2HtmlConverter());
					break;

				case Constant.PPT_SUFFIX:
					filesuper = new FileContext(new PPT2PDFConverter());
					break;
				case Constant.PPTX_SUFFIX:
					filesuper = new FileContext(new PPTX2PDFConverter());
					break;
				case Constant.PDF_SUFFIX:
					outPath = fp.getFilepath();
					outPath = outPath.replace("/Users/appleone/Documents/", "");
					fp.setFilepath(outPath);
					impl.addFilePreview(fp);
					return fp.getPreviewfilepath();
				}

				if (filesuper == null) {
					return "FileType not supported!";
					// System.out.println("FileType not supported!");
				} else {

					filesuper.execute(fp.getFilepath(), outPath);
					outPath = outPath.replace("/Users/appleone/Documents/", "");
					fp.setFilepath(outPath);
					impl.addFilePreview(fp);
					return fp.getPreviewfilepath();
				}
			} else {
				return fp.getPreviewfilepath();
			}
		} else {
			return "no such file in current database";
		}
	}

}
