/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.dao.implementation;

import co.com.poli.examenfinalpdp.dao.ICursoDao;
import co.com.poli.examenfinalpdp.jpacontroller.TblcursoJpaController;
import co.com.poli.examenfinalpdp.model.Tblcurso;
import co.com.poli.examenfinalpdp.util.JPAFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SANTIAGO
 */
public class CursoDaoImpl implements ICursoDao {

    @Override
    public List<Tblcurso> obtenerListadocursos() {
        List<Tblcurso> listado = new ArrayList<>();
        TblcursoJpaController tblcursoJpaController = new TblcursoJpaController(JPAFactory.getFACTORY());
        listado = tblcursoJpaController.findTblcursoEntities();
        return listado;
    }

    @Override
    public Boolean crearCurso(Tblcurso curso) {
        Boolean sw = false;
        try {
            TblcursoJpaController tblcursoJpaController = new TblcursoJpaController(JPAFactory.getFACTORY());
            tblcursoJpaController.create(curso);
        } catch (Exception ex) {
            sw = true;
            Logger.getLogger(CursoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }

    @Override
    public Tblcurso obtenercurso(String idmateria) {
        TblcursoJpaController tblcursoJpaController = new TblcursoJpaController(JPAFactory.getFACTORY());
        return tblcursoJpaController.findTblcurso(idmateria);
    }

}
