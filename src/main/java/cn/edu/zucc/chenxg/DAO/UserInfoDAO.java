package cn.edu.zucc.chenxg.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cn.edu.zucc.chenxg.model.JPAUtil;
import cn.edu.zucc.chenxg.model.UserInfoJpaController;
import cn.edu.zucc.chenxg.model.Userinfo;

public class UserInfoDAO {
	
	EntityManagerFactory emf = null;
	UserInfoJpaController fpjc = null;
	EntityManager em = null;
	List<Userinfo> UserInfos = null;
	Userinfo userInfo =null;
	Userinfo FileInfo = null;
	String sql = null;
	
	public Userinfo getUserInfoByUserID(String Userid) {
		emf = Persistence.createEntityManagerFactory(JPAUtil.JPA);
		em = emf.createEntityManager();
		sql = JPAUtil.SELECT_USERINFO_BY_USERID;
		Query query = em.createNativeQuery(sql, Userinfo.class);
		query.setParameter(1, Userid);
		userInfo = (Userinfo) query.getResultList().get(0);
		return userInfo;
	}
	

}
