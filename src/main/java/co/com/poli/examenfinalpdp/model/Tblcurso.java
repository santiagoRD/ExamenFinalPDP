/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SANTIAGO
 */
@Entity
@Table(name = "tblcurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcurso.findAll", query = "SELECT t FROM Tblcurso t")
    , @NamedQuery(name = "Tblcurso.findByIdmateria", query = "SELECT t FROM Tblcurso t WHERE t.idmateria = :idmateria")
    , @NamedQuery(name = "Tblcurso.findByNombre", query = "SELECT t FROM Tblcurso t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tblcurso.findByNumcreditos", query = "SELECT t FROM Tblcurso t WHERE t.numcreditos = :numcreditos")
    , @NamedQuery(name = "Tblcurso.findByCupomax", query = "SELECT t FROM Tblcurso t WHERE t.cupomax = :cupomax")
    , @NamedQuery(name = "Tblcurso.findByCupomin", query = "SELECT t FROM Tblcurso t WHERE t.cupomin = :cupomin")})
public class Tblcurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "idmateria")
    private String idmateria;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "numcreditos")
    private Double numcreditos;
    @Column(name = "cupomax")
    private Integer cupomax;
    @Column(name = "cupomin")
    private Integer cupomin;
    @OneToMany(mappedBy = "curso")
    private Collection<Tblinscripciones> tblinscripcionesCollection;

    public Tblcurso() {
    }

    public Tblcurso(String idmateria) {
        this.idmateria = idmateria;
    }

    public Tblcurso(String idmateria, String nombre, Double numcreditos, Integer cupomax, Integer cupomin) {
        this.idmateria = idmateria;
        this.nombre = nombre;
        this.numcreditos = numcreditos;
        this.cupomax = cupomax;
        this.cupomin = cupomin;
    }

    


    
    public String getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(String idmateria) {
        this.idmateria = idmateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getNumcreditos() {
        return numcreditos;
    }

    public void setNumcreditos(Double numcreditos) {
        this.numcreditos = numcreditos;
    }

    public Integer getCupomax() {
        return cupomax;
    }

    public void setCupomax(Integer cupomax) {
        this.cupomax = cupomax;
    }

    public Integer getCupomin() {
        return cupomin;
    }

    public void setCupomin(Integer cupomin) {
        this.cupomin = cupomin;
    }

    @XmlTransient
    public Collection<Tblinscripciones> getTblinscripcionesCollection() {
        return tblinscripcionesCollection;
    }

    public void setTblinscripcionesCollection(Collection<Tblinscripciones> tblinscripcionesCollection) {
        this.tblinscripcionesCollection = tblinscripcionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmateria != null ? idmateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcurso)) {
            return false;
        }
        Tblcurso other = (Tblcurso) object;
        if ((this.idmateria == null && other.idmateria != null) || (this.idmateria != null && !this.idmateria.equals(other.idmateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.poli.examenfinalpdp.model.Tblcurso[ idmateria=" + idmateria + " ]";
    }
    
}
