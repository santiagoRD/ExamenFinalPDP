/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.dao;

import co.com.poli.examenfinalpdp.model.Tblinscripciones;
import java.util.List;

/**
 *
 * @author SANTIAGO
 */
public interface IInscripcionesDao {
    
    List<Tblinscripciones>obtenerListainscripciones();
    Boolean crearInscripcion(Tblinscripciones inscripcion);
    Tblinscripciones obtenerInscripcion(String idinscripcion);
}
