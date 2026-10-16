/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BRILOF
 */
@Entity
@Table(name = "classement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classement.findAll", query = "SELECT c FROM Classement c")
    , @NamedQuery(name = "Classement.findByIdEquipe", query = "SELECT c FROM Classement c WHERE c.idEquipe = :idEquipe")
    , @NamedQuery(name = "Classement.findByMatchJoues", query = "SELECT c FROM Classement c WHERE c.matchJoues = :matchJoues")
    , @NamedQuery(name = "Classement.findByVictoires", query = "SELECT c FROM Classement c WHERE c.victoires = :victoires")
    , @NamedQuery(name = "Classement.findByNulls", query = "SELECT c FROM Classement c WHERE c.nulls = :nulls")
    , @NamedQuery(name = "Classement.findByDefaites", query = "SELECT c FROM Classement c WHERE c.defaites = :defaites")
    , @NamedQuery(name = "Classement.findByButsMarques", query = "SELECT c FROM Classement c WHERE c.butsMarques = :butsMarques")
    , @NamedQuery(name = "Classement.findByButsEncaisses", query = "SELECT c FROM Classement c WHERE c.butsEncaisses = :butsEncaisses")
    , @NamedQuery(name = "Classement.findByPoint", query = "SELECT c FROM Classement c WHERE c.point = :point")})
public class Classement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EQUIPE")
    private Integer idEquipe;
    @Column(name = "MATCH_JOUES")
    private Integer matchJoues;
    @Column(name = "VICTOIRES")
    private Integer victoires;
    @Column(name = "NULLS")
    private Integer nulls;
    @Column(name = "DEFAITES")
    private Integer defaites;
    @Column(name = "BUTS_MARQUES")
    private Integer butsMarques;
    @Column(name = "BUTS_ENCAISSES")
    private Integer butsEncaisses;
    @Column(name = "POINT")
    private Integer point;
    @JoinColumn(name = "ID_EQUIPE", referencedColumnName = "ID_EQUIPE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Equipes equipes;

    public Classement() {
    }

    public Classement(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Integer getMatchJoues() {
        return matchJoues;
    }

    public void setMatchJoues(Integer matchJoues) {
        this.matchJoues = matchJoues;
    }

    public Integer getVictoires() {
        return victoires;
    }

    public void setVictoires(Integer victoires) {
        this.victoires = victoires;
    }

    public Integer getNulls() {
        return nulls;
    }

    public void setNulls(Integer nulls) {
        this.nulls = nulls;
    }

    public Integer getDefaites() {
        return defaites;
    }

    public void setDefaites(Integer defaites) {
        this.defaites = defaites;
    }

    public Integer getButsMarques() {
        return butsMarques;
    }

    public void setButsMarques(Integer butsMarques) {
        this.butsMarques = butsMarques;
    }

    public Integer getButsEncaisses() {
        return butsEncaisses;
    }

    public void setButsEncaisses(Integer butsEncaisses) {
        this.butsEncaisses = butsEncaisses;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Equipes getEquipes() {
        return equipes;
    }

    public void setEquipes(Equipes equipes) {
        this.equipes = equipes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipe != null ? idEquipe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classement)) {
            return false;
        }
        Classement other = (Classement) object;
        if ((this.idEquipe == null && other.idEquipe != null) || (this.idEquipe != null && !this.idEquipe.equals(other.idEquipe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Classement[ idEquipe=" + idEquipe + " ]";
    }
    
}
