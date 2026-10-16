/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
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
 * @author BRILOF
 */
@Entity
@Table(name = "saison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saison.findAll", query = "SELECT s FROM Saison s")
    , @NamedQuery(name = "Saison.findByIdSaison", query = "SELECT s FROM Saison s WHERE s.idSaison = :idSaison")
    , @NamedQuery(name = "Saison.findByNomSaison", query = "SELECT s FROM Saison s WHERE s.nomSaison = :nomSaison")
    , @NamedQuery(name = "Saison.findByStatutSaison", query = "SELECT s FROM Saison s WHERE s.statutSaison = :statutSaison")})
public class Saison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SAISON")
    private Integer idSaison;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOM_SAISON")
    private String nomSaison;
    @Size(max = 15)
    @Column(name = "STATUT_SAISON")
    private String statutSaison;
    @OneToMany(mappedBy = "idSaison")
    private List<Matchs> matchsList;

    public Saison() {
    }

    public Saison(Integer idSaison) {
        this.idSaison = idSaison;
    }

    public Saison(Integer idSaison, String nomSaison) {
        this.idSaison = idSaison;
        this.nomSaison = nomSaison;
    }

    public Integer getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(Integer idSaison) {
        this.idSaison = idSaison;
    }

    public String getNomSaison() {
        return nomSaison;
    }

    public void setNomSaison(String nomSaison) {
        this.nomSaison = nomSaison;
    }

    public String getStatutSaison() {
        return statutSaison;
    }

    public void setStatutSaison(String statutSaison) {
        this.statutSaison = statutSaison;
    }

    @XmlTransient
    public List<Matchs> getMatchsList() {
        return matchsList;
    }

    public void setMatchsList(List<Matchs> matchsList) {
        this.matchsList = matchsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSaison != null ? idSaison.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saison)) {
            return false;
        }
        Saison other = (Saison) object;
        if ((this.idSaison == null && other.idSaison != null) || (this.idSaison != null && !this.idSaison.equals(other.idSaison))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Saison[ idSaison=" + idSaison + " ]";
    }
    
}
