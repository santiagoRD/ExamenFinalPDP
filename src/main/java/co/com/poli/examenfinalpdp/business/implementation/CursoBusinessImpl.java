/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.business.implementation;

import co.com.poli.examenfinalpdp.business.ICursoBusiness;
import co.com.poli.examenfinalpdp.dao.implementation.CursoDaoImpl;
import co.com.poli.examenfinalpdp.model.Tblcurso;
import java.util.List;

/**
 *
 * @author SANTIAGO
 */
public class CursoBusinessImpl implements ICursoBusiness{

    private CursoDaoImpl cursoDaoImpl = new CursoDaoImpl();
    @Override
    public List<Tblcurso> obtenerListacursos() {
        return cursoDaoImpl.obtenerListadocursos();
    }

    @Override
    public Boolean crearCurso(Tblcurso curso) {
        return cursoDaoImpl.crearCurso(curso);
    }

    @Override
    public Tblcurso obtenerCurso(String idmateria) {
        return cursoDaoImpl.obtenercurso(idmateria);
    }
    
}
