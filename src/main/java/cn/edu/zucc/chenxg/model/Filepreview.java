package cn.edu.zucc.chenxg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the filepreview database table.
 * 
 */
@Entity
@NamedQuery(name="Filepreview.findAll", query="SELECT f FROM Filepreview f")
public class Filepreview  {
//	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Id 
	@SequenceGenerator(name="pk_sequence",sequenceName="filepreview_sid_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name="sid", unique=true, nullable=false)
	private Integer sid;

	private String filemd5;

	private String filepath;

	@Temporal(TemporalType.DATE)
	private Date lastpreviewtime;

	private Integer previewcount;

	private String previewfilepath;

	private String uploaduserid;

	public Filepreview() {
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getFilemd5() {
		return this.filemd5;
	}

	public void setFilemd5(String filemd5) {
		this.filemd5 = filemd5;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getLastpreviewtime() {
		return this.lastpreviewtime;
	}

	public void setLastpreviewtime(Date lastpreviewtime) {
		this.lastpreviewtime = lastpreviewtime;
	}

	public Integer getPreviewcount() {
		return this.previewcount;
	}

	public void setPreviewcount(Integer previewcount) {
		this.previewcount = previewcount;
	}

	public String getPreviewfilepath() {
		return this.previewfilepath;
	}

	public void setPreviewfilepath(String previewfilepath) {
		this.previewfilepath = previewfilepath;
	}

	public String getUploaduserid() {
		return this.uploaduserid;
	}

	public void setUploaduserid(String uploaduserid) {
		this.uploaduserid = uploaduserid;
	}

}