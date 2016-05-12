package cn.edu.zucc.chenxg.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cn.edu.zucc.chenxg.model.FilePreviewJpaController;
import cn.edu.zucc.chenxg.model.Filepreview;
import cn.edu.zucc.chenxg.model.JPAUtil;

public class FilePreviewDAO {

	EntityManagerFactory emf = null;
	FilePreviewJpaController fpjc = null;
	EntityManager em = null;
	List<Filepreview> FileInfos = null;
	Filepreview FileInfo = null;
	String sql = null;

	// @Override
	// public String isPreviewExists(String inPath) {
	// emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
	// fpjc = new FilePreviewJpaController(emf);
	// em = emf.createEntityManager();
	// sql = JPAUtil.SELECT_PREVIEW_BY_INPATH;
	// Query query = em.createNativeQuery(sql);
	// query.setParameter(1, inPath);
	// String path = (String) query.getSingleResult();
	// if (FileInfos != null) {
	// return path;
	// } else
	// return null;
	//
	// }

	public List<Filepreview> isPreviewExists(int sid) {
		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
		em = emf.createEntityManager();
		sql = JPAUtil.SELECT_PREVIEW_BY_SID;
		Query query = em.createNativeQuery(sql, Filepreview.class);
		query.setParameter(1, sid);
		FileInfos = query.getResultList();
		return FileInfos;
	}

	public List<Filepreview> getFileByUser(String UserID) {
		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
		em = emf.createEntityManager();
		sql = JPAUtil.SELECT_PREVIEW_BY_USER;
		Query query = em.createNativeQuery(sql, Filepreview.class);
		query.setParameter(1, UserID);
		List<Filepreview> fps = query.getResultList();
		return fps;
	}

//	public boolean createFilePreview(Filepreview fp) {
//		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
//		em = emf.createEntityManager();
//		sql = JPAUtil.CREATE_FILEPREVIEW;
//		Query query = em.createNativeQuery(sql, FilePreview.class);
//		query.setParameter(1, fp.getFilePath());
//		query.setParameter(2, fp.getPreviewFilePath());
//		query.setParameter(3, fp.getPreviewCount());
//		query.setParameter(4, fp.getLastPreviewTime());
//		query.setParameter(5, fp.getFileMD5());
//		query.setParameter(6, fp.getUploadUserID());
//
//		// (filepath,perviewfilepath,previewcount,lastpreviewtime,fileMD5,uploaduserid)
//		// values (?,?,?,?,?,?)";
//		query.getResultList();
//		return true;
//		// return fps;
//	}

	public List<Filepreview> selectAllFile() {
		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
		fpjc = new FilePreviewJpaController(emf);
		FileInfos = fpjc.findFilePreviewEntities();

		return FileInfos;

	}

	public boolean addFilePreview(Filepreview fp) {
		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
		fpjc = new FilePreviewJpaController(emf);
		try {

			fpjc.edit(fp);
			return true;
		} catch (Exception ex) {
			Logger.getLogger(FilePreviewDAO.class.getName()).log(Level.SEVERE, null, ex);
			return true;
		}
	}

	public boolean createFile(Filepreview fp) {
		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
		fpjc = new FilePreviewJpaController(emf);
		try {

			fpjc.create(fp);
			return true;
		} catch (Exception ex) {
			Logger.getLogger(FilePreviewDAO.class.getName()).log(Level.SEVERE, null, ex);
			return true;
		}
	}

}
