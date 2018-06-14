/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.business;

import co.com.poli.examenfinalpdp.model.Tblcurso;
import java.util.List;

/**
 *
 * @author SANTIAGO
 */
public interface ICursoBusiness {
    
    List<Tblcurso>obtenerListacursos();
    Boolean crearCurso(Tblcurso curso);
    Tblcurso obtenerCurso(String idmateria);
    
}
