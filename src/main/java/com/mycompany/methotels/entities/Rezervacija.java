/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Fedor
 */
@Entity
@Table(name = "rezervacija")
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r")})
public class Rezervacija extends AbstractEntity{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @JoinColumn(name = "IDGOST", referencedColumnName = "ID")
    @ManyToOne
    private Gost idgost;
    @JoinColumn(name = "IDSOBA", referencedColumnName = "ID")
    @ManyToOne
    private Soba idsoba;
    @OneToMany(mappedBy = "idrez")
    private List<Gost> gostList;
    @OneToMany(mappedBy = "idrez")
    private List<Soba> sobaList;

    @Inject
    public Rezervacija() {
    }

    public Rezervacija(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gost getIdgost() {
        return idgost;
    }

    public void setIdgost(Gost idgost) {
        this.idgost = idgost;
    }

    public Soba getIdsoba() {
        return idsoba;
    }

    public void setIdsoba(Soba idsoba) {
        this.idsoba = idsoba;
    }

    public List<Gost> getGostList() {
        return gostList;
    }

    public void setGostList(List<Gost> gostList) {
        this.gostList = gostList;
    }

    public List<Soba> getSobaList() {
        return sobaList;
    }

    public void setSobaList(List<Soba> sobaList) {
        this.sobaList = sobaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.methotels.entities.Rezervacija[ id=" + id + " ]";
    }
    
}
