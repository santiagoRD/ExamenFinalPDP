/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SANTIAGO
 */
@Entity
@Table(name = "tblinscripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblinscripciones.findAll", query = "SELECT t FROM Tblinscripciones t")
    , @NamedQuery(name = "Tblinscripciones.findByIdinscripcion", query = "SELECT t FROM Tblinscripciones t WHERE t.idinscripcion = :idinscripcion")
    , @NamedQuery(name = "Tblinscripciones.findByDocumento", query = "SELECT t FROM Tblinscripciones t WHERE t.documento = :documento")
    , @NamedQuery(name = "Tblinscripciones.findByEstrato", query = "SELECT t FROM Tblinscripciones t WHERE t.estrato = :estrato")
    , @NamedQuery(name = "Tblinscripciones.findByValorcredito", query = "SELECT t FROM Tblinscripciones t WHERE t.valorcredito = :valorcredito")})
public class Tblinscripciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "idinscripcion")
    private String idinscripcion;
    @Size(max = 2147483647)
    @Column(name = "documento")
    private String documento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "estrato")
    private Double estrato;
    @Column(name = "valorcredito")
    private Double valorcredito;
    @JoinColumn(name = "curso", referencedColumnName = "idmateria")
    @ManyToOne
    private Tblcurso curso;

    public Tblinscripciones() {
    }

    public Tblinscripciones(String idinscripcion) {
        this.idinscripcion = idinscripcion;
    }

    public Tblinscripciones(String idinscripcion, String documento, Double estrato, Tblcurso curso) {
        this.idinscripcion = idinscripcion;
        this.documento = documento;
        this.estrato = estrato;
        this.curso = curso;
    }
    
    

    public String getIdinscripcion() {
        return idinscripcion;
    }

    public void setIdinscripcion(String idinscripcion) {
        this.idinscripcion = idinscripcion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Double getEstrato() {
        return estrato;
    }

    public void setEstrato(Double estrato) {
        this.estrato = estrato;
    }

    public Double getValorcredito() {
        return valorcredito;
    }

    public void setValorcredito(Double valorcredito) {
        this.valorcredito = valorcredito;
    }

    public Tblcurso getCurso() {
        return curso;
    }

    public void setCurso(Tblcurso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinscripcion != null ? idinscripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblinscripciones)) {
            return false;
        }
        Tblinscripciones other = (Tblinscripciones) object;
        if ((this.idinscripcion == null && other.idinscripcion != null) || (this.idinscripcion != null && !this.idinscripcion.equals(other.idinscripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.poli.examenfinalpdp.model.Tblinscripciones[ idinscripcion=" + idinscripcion + " ]";
    }
    
}
