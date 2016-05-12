package cn.edu.zucc.chenxg.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Userinfo.findAll", query="SELECT u FROM Userinfo u")
public class Userinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sid;

	@Column(name="user_desc")
	private String userDesc;

	@Column(name="user_password")
	private String userPassword;

	private String userid;

	private String username;

	public Userinfo() {
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}