/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.dao.implementation;

import co.com.poli.examenfinalpdp.dao.IInscripcionesDao;
import co.com.poli.examenfinalpdp.jpacontroller.TblinscripcionesJpaController;
import co.com.poli.examenfinalpdp.model.Tblinscripciones;
import co.com.poli.examenfinalpdp.util.JPAFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SANTIAGO
 */
public class InscripcionDaoImpl implements IInscripcionesDao{

    @Override
    public List<Tblinscripciones> obtenerListainscripciones() {
        List<Tblinscripciones>listado = new ArrayList<>();
        TblinscripcionesJpaController tblinscripcionesJpaController = new TblinscripcionesJpaController(JPAFactory.getFACTORY());
        listado = tblinscripcionesJpaController.findTblinscripcionesEntities();
        return listado;
    }

    @Override
    public Boolean crearInscripcion(Tblinscripciones inscripcion) {
        Boolean sw = false;
        try {
            TblinscripcionesJpaController tblinscripcionesJpaController = new TblinscripcionesJpaController(JPAFactory.getFACTORY());
            tblinscripcionesJpaController.create(inscripcion);
        } catch (Exception ex) {
            sw = true;
            Logger.getLogger(InscripcionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }

    @Override
    public Tblinscripciones obtenerInscripcion(String idinscripcion) {
        TblinscripcionesJpaController tblinscripcionesJpaController = new TblinscripcionesJpaController(JPAFactory.getFACTORY());
        return tblinscripcionesJpaController.findTblinscripciones(idinscripcion);
    }
    
}
