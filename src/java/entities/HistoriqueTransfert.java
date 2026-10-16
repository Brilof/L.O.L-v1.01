/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BRILOF
 */
@Entity
@Table(name = "historique_transfert")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriqueTransfert.findAll", query = "SELECT h FROM HistoriqueTransfert h")
    , @NamedQuery(name = "HistoriqueTransfert.findByIdTransfert", query = "SELECT h FROM HistoriqueTransfert h WHERE h.idTransfert = :idTransfert")
    , @NamedQuery(name = "HistoriqueTransfert.findByDateTransfert", query = "SELECT h FROM HistoriqueTransfert h WHERE h.dateTransfert = :dateTransfert")})
public class HistoriqueTransfert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TRANSFERT")
    private Integer idTransfert;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_TRANSFERT")
    @Temporal(TemporalType.DATE)
    private Date dateTransfert;
    @JoinColumn(name = "ID_LAST_CLUB", referencedColumnName = "ID_EQUIPE")
    @ManyToOne
    private Equipes idLastClub;
    @JoinColumn(name = "ID_NEW_CLUB", referencedColumnName = "ID_EQUIPE")
    @ManyToOne
    private Equipes idNewClub;
    @JoinColumn(name = "ID_JOUEUR", referencedColumnName = "ID_JOUEUR")
    @ManyToOne
    private Joueurs idJoueur;

    public HistoriqueTransfert() {
    }

    public HistoriqueTransfert(Integer idTransfert) {
        this.idTransfert = idTransfert;
    }

    public HistoriqueTransfert(Integer idTransfert, Date dateTransfert) {
        this.idTransfert = idTransfert;
        this.dateTransfert = dateTransfert;
    }

    public Integer getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(Integer idTransfert) {
        this.idTransfert = idTransfert;
    }

    public Date getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(Date dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    public Equipes getIdLastClub() {
        return idLastClub;
    }

    public void setIdLastClub(Equipes idLastClub) {
        this.idLastClub = idLastClub;
    }

    public Equipes getIdNewClub() {
        return idNewClub;
    }

    public void setIdNewClub(Equipes idNewClub) {
        this.idNewClub = idNewClub;
    }

    public Joueurs getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Joueurs idJoueur) {
        this.idJoueur = idJoueur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransfert != null ? idTransfert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriqueTransfert)) {
            return false;
        }
        HistoriqueTransfert other = (HistoriqueTransfert) object;
        if ((this.idTransfert == null && other.idTransfert != null) || (this.idTransfert != null && !this.idTransfert.equals(other.idTransfert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.HistoriqueTransfert[ idTransfert=" + idTransfert + " ]";
    }
    
}
