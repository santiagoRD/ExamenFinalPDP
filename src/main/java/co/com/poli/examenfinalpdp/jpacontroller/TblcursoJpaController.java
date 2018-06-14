/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.jpacontroller;

import co.com.poli.examenfinalpdp.jpacontroller.exceptions.NonexistentEntityException;
import co.com.poli.examenfinalpdp.jpacontroller.exceptions.PreexistingEntityException;
import co.com.poli.examenfinalpdp.model.Tblcurso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.poli.examenfinalpdp.model.Tblinscripciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author SANTIAGO
 */
public class TblcursoJpaController implements Serializable {

    public TblcursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblcurso tblcurso) throws PreexistingEntityException, Exception {
        if (tblcurso.getTblinscripcionesCollection() == null) {
            tblcurso.setTblinscripcionesCollection(new ArrayList<Tblinscripciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblinscripciones> attachedTblinscripcionesCollection = new ArrayList<Tblinscripciones>();
            for (Tblinscripciones tblinscripcionesCollectionTblinscripcionesToAttach : tblcurso.getTblinscripcionesCollection()) {
                tblinscripcionesCollectionTblinscripcionesToAttach = em.getReference(tblinscripcionesCollectionTblinscripcionesToAttach.getClass(), tblinscripcionesCollectionTblinscripcionesToAttach.getIdinscripcion());
                attachedTblinscripcionesCollection.add(tblinscripcionesCollectionTblinscripcionesToAttach);
            }
            tblcurso.setTblinscripcionesCollection(attachedTblinscripcionesCollection);
            em.persist(tblcurso);
            for (Tblinscripciones tblinscripcionesCollectionTblinscripciones : tblcurso.getTblinscripcionesCollection()) {
                Tblcurso oldCursoOfTblinscripcionesCollectionTblinscripciones = tblinscripcionesCollectionTblinscripciones.getCurso();
                tblinscripcionesCollectionTblinscripciones.setCurso(tblcurso);
                tblinscripcionesCollectionTblinscripciones = em.merge(tblinscripcionesCollectionTblinscripciones);
                if (oldCursoOfTblinscripcionesCollectionTblinscripciones != null) {
                    oldCursoOfTblinscripcionesCollectionTblinscripciones.getTblinscripcionesCollection().remove(tblinscripcionesCollectionTblinscripciones);
                    oldCursoOfTblinscripcionesCollectionTblinscripciones = em.merge(oldCursoOfTblinscripcionesCollectionTblinscripciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTblcurso(tblcurso.getIdmateria()) != null) {
                throw new PreexistingEntityException("Tblcurso " + tblcurso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblcurso tblcurso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcurso persistentTblcurso = em.find(Tblcurso.class, tblcurso.getIdmateria());
            Collection<Tblinscripciones> tblinscripcionesCollectionOld = persistentTblcurso.getTblinscripcionesCollection();
            Collection<Tblinscripciones> tblinscripcionesCollectionNew = tblcurso.getTblinscripcionesCollection();
            Collection<Tblinscripciones> attachedTblinscripcionesCollectionNew = new ArrayList<Tblinscripciones>();
            for (Tblinscripciones tblinscripcionesCollectionNewTblinscripcionesToAttach : tblinscripcionesCollectionNew) {
                tblinscripcionesCollectionNewTblinscripcionesToAttach = em.getReference(tblinscripcionesCollectionNewTblinscripcionesToAttach.getClass(), tblinscripcionesCollectionNewTblinscripcionesToAttach.getIdinscripcion());
                attachedTblinscripcionesCollectionNew.add(tblinscripcionesCollectionNewTblinscripcionesToAttach);
            }
            tblinscripcionesCollectionNew = attachedTblinscripcionesCollectionNew;
            tblcurso.setTblinscripcionesCollection(tblinscripcionesCollectionNew);
            tblcurso = em.merge(tblcurso);
            for (Tblinscripciones tblinscripcionesCollectionOldTblinscripciones : tblinscripcionesCollectionOld) {
                if (!tblinscripcionesCollectionNew.contains(tblinscripcionesCollectionOldTblinscripciones)) {
                    tblinscripcionesCollectionOldTblinscripciones.setCurso(null);
                    tblinscripcionesCollectionOldTblinscripciones = em.merge(tblinscripcionesCollectionOldTblinscripciones);
                }
            }
            for (Tblinscripciones tblinscripcionesCollectionNewTblinscripciones : tblinscripcionesCollectionNew) {
                if (!tblinscripcionesCollectionOld.contains(tblinscripcionesCollectionNewTblinscripciones)) {
                    Tblcurso oldCursoOfTblinscripcionesCollectionNewTblinscripciones = tblinscripcionesCollectionNewTblinscripciones.getCurso();
                    tblinscripcionesCollectionNewTblinscripciones.setCurso(tblcurso);
                    tblinscripcionesCollectionNewTblinscripciones = em.merge(tblinscripcionesCollectionNewTblinscripciones);
                    if (oldCursoOfTblinscripcionesCollectionNewTblinscripciones != null && !oldCursoOfTblinscripcionesCollectionNewTblinscripciones.equals(tblcurso)) {
                        oldCursoOfTblinscripcionesCollectionNewTblinscripciones.getTblinscripcionesCollection().remove(tblinscripcionesCollectionNewTblinscripciones);
                        oldCursoOfTblinscripcionesCollectionNewTblinscripciones = em.merge(oldCursoOfTblinscripcionesCollectionNewTblinscripciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tblcurso.getIdmateria();
                if (findTblcurso(id) == null) {
                    throw new NonexistentEntityException("The tblcurso with id " + id + " no longer exists.");
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
            Tblcurso tblcurso;
            try {
                tblcurso = em.getReference(Tblcurso.class, id);
                tblcurso.getIdmateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblcurso with id " + id + " no longer exists.", enfe);
            }
            Collection<Tblinscripciones> tblinscripcionesCollection = tblcurso.getTblinscripcionesCollection();
            for (Tblinscripciones tblinscripcionesCollectionTblinscripciones : tblinscripcionesCollection) {
                tblinscripcionesCollectionTblinscripciones.setCurso(null);
                tblinscripcionesCollectionTblinscripciones = em.merge(tblinscripcionesCollectionTblinscripciones);
            }
            em.remove(tblcurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblcurso> findTblcursoEntities() {
        return findTblcursoEntities(true, -1, -1);
    }

    public List<Tblcurso> findTblcursoEntities(int maxResults, int firstResult) {
        return findTblcursoEntities(false, maxResults, firstResult);
    }

    private List<Tblcurso> findTblcursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblcurso.class));
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

    public Tblcurso findTblcurso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblcurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblcursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblcurso> rt = cq.from(Tblcurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
