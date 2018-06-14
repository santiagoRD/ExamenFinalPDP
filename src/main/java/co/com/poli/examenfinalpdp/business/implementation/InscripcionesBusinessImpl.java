/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.business.implementation;

import co.com.poli.examenfinalpdp.business.IInscripcionBusiness;
import co.com.poli.examenfinalpdp.dao.implementation.InscripcionDaoImpl;
import co.com.poli.examenfinalpdp.model.Tblinscripciones;
import java.util.List;

/**
 *
 * @author SANTIAGO
 */
public class InscripcionesBusinessImpl implements IInscripcionBusiness{

    private InscripcionDaoImpl inscripcionDaoImpl = new InscripcionDaoImpl();
    @Override
    public List<Tblinscripciones> obtenerListainscripciones() {
        return inscripcionDaoImpl.obtenerListainscripciones();
    }

    @Override
    public Boolean crearInscripcion(Tblinscripciones inscripcion) {
        return inscripcionDaoImpl.crearInscripcion(inscripcion);
    }

    @Override
    public Tblinscripciones obtenerInscripcion(String idinscriipcion) {
        return inscripcionDaoImpl.obtenerInscripcion(idinscriipcion);
    }

    @Override
    public Boolean validarEstrato(double estrato) {
        Boolean sw = false;
        if(estrato != 1 || estrato != 2){
            sw = true;
        }
        return sw;
    }

    @Override
    public int calcularValorcredito(int estrato) {
        int valor;
        if(estrato == 1){
            valor = 25000;
        }else{
            valor = 30000;
        }
        return valor;
    }
    
}
