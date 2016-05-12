package cn.edu.zucc.chenxg.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cn.edu.zucc.chenxg.model.exceptions.NonexistentEntityException;

/**
 *
 * @author appleone
 */
public class UserInfoJpaController implements Serializable {

    public UserInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Userinfo userInfo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Userinfo userInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userInfo = em.merge(userInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userInfo.getSid();
                if (findUserInfo(id) == null) {
                    throw new NonexistentEntityException("The userinfo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userinfo userInfo;
            try {
                userInfo = em.getReference(Userinfo.class, id);
                userInfo.getSid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userinfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(userInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Userinfo> findUserInfoEntities() {
        return findUserInfoEntities(true, -1, -1);
    }

    public List<Userinfo> findUserInfoEntities(int maxResults, int firstResult) {
        return findUserInfoEntities(false, maxResults, firstResult);
    }

    private List<Userinfo> findUserInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Userinfo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Userinfo findUserInfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Userinfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Userinfo> rt = cq.from(Userinfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

