/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.zucc.chenxg.model;
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
public class FilePreviewJpaController implements Serializable {

    public FilePreviewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Filepreview filePreview) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(filePreview);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Filepreview filePreview) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            filePreview = em.merge(filePreview);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = filePreview.getSid();
                if (findFilePreview(id) == null) {
                    throw new NonexistentEntityException("The filePreview with id " + id + " no longer exists.");
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
            Filepreview filePreview;
            try {
                filePreview = em.getReference(Filepreview.class, id);
                filePreview.getSid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The filePreview with id " + id + " no longer exists.", enfe);
            }
            em.remove(filePreview);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Filepreview> findFilePreviewEntities() {
        return findFilePreviewEntities(true, -1, -1);
    }

    public List<Filepreview> findFilePreviewEntities(int maxResults, int firstResult) {
        return findFilePreviewEntities(false, maxResults, firstResult);
    }

    private List<Filepreview> findFilePreviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Filepreview.class));
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

    public Filepreview findFilePreview(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Filepreview.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilePreviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Filepreview> rt = cq.from(Filepreview.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
