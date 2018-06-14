/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.jpacontroller;

import co.com.poli.examenfinalpdp.jpacontroller.exceptions.NonexistentEntityException;
import co.com.poli.examenfinalpdp.jpacontroller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.poli.examenfinalpdp.model.Tblcurso;
import co.com.poli.examenfinalpdp.model.Tblinscripciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author SANTIAGO
 */
public class TblinscripcionesJpaController implements Serializable {

    public TblinscripcionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblinscripciones tblinscripciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcurso curso = tblinscripciones.getCurso();
            if (curso != null) {
                curso = em.getReference(curso.getClass(), curso.getIdmateria());
                tblinscripciones.setCurso(curso);
            }
            em.persist(tblinscripciones);
            if (curso != null) {
                curso.getTblinscripcionesCollection().add(tblinscripciones);
                curso = em.merge(curso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTblinscripciones(tblinscripciones.getIdinscripcion()) != null) {
                throw new PreexistingEntityException("Tblinscripciones " + tblinscripciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblinscripciones tblinscripciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblinscripciones persistentTblinscripciones = em.find(Tblinscripciones.class, tblinscripciones.getIdinscripcion());
            Tblcurso cursoOld = persistentTblinscripciones.getCurso();
            Tblcurso cursoNew = tblinscripciones.getCurso();
            if (cursoNew != null) {
                cursoNew = em.getReference(cursoNew.getClass(), cursoNew.getIdmateria());
                tblinscripciones.setCurso(cursoNew);
            }
            tblinscripciones = em.merge(tblinscripciones);
            if (cursoOld != null && !cursoOld.equals(cursoNew)) {
                cursoOld.getTblinscripcionesCollection().remove(tblinscripciones);
                cursoOld = em.merge(cursoOld);
            }
            if (cursoNew != null && !cursoNew.equals(cursoOld)) {
                cursoNew.getTblinscripcionesCollection().add(tblinscripciones);
                cursoNew = em.merge(cursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tblinscripciones.getIdinscripcion();
                if (findTblinscripciones(id) == null) {
                    throw new NonexistentEntityException("The tblinscripciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblinscripciones tblinscripciones;
            try {
                tblinscripciones = em.getReference(Tblinscripciones.class, id);
                tblinscripciones.getIdinscripcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblinscripciones with id " + id + " no longer exists.", enfe);
            }
            Tblcurso curso = tblinscripciones.getCurso();
            if (curso != null) {
                curso.getTblinscripcionesCollection().remove(tblinscripciones);
                curso = em.merge(curso);
            }
            em.remove(tblinscripciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblinscripciones> findTblinscripcionesEntities() {
        return findTblinscripcionesEntities(true, -1, -1);
    }

    public List<Tblinscripciones> findTblinscripcionesEntities(int maxResults, int firstResult) {
        return findTblinscripcionesEntities(false, maxResults, firstResult);
    }

    private List<Tblinscripciones> findTblinscripcionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblinscripciones.class));
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

    public Tblinscripciones findTblinscripciones(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblinscripciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblinscripcionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblinscripciones> rt = cq.from(Tblinscripciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
