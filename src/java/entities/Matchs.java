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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BRILOF
 */
@Entity
@Table(name = "matchs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matchs.findAll", query = "SELECT m FROM Matchs m")
    , @NamedQuery(name = "Matchs.findByIdMatch", query = "SELECT m FROM Matchs m WHERE m.idMatch = :idMatch")
    , @NamedQuery(name = "Matchs.findByDateMatch", query = "SELECT m FROM Matchs m WHERE m.dateMatch = :dateMatch")
    , @NamedQuery(name = "Matchs.findByArbitre", query = "SELECT m FROM Matchs m WHERE m.arbitre = :arbitre")
    , @NamedQuery(name = "Matchs.findByButsDomicile", query = "SELECT m FROM Matchs m WHERE m.butsDomicile = :butsDomicile")
    , @NamedQuery(name = "Matchs.findByButsExterieur", query = "SELECT m FROM Matchs m WHERE m.butsExterieur = :butsExterieur")
    , @NamedQuery(name = "Matchs.findByStatut", query = "SELECT m FROM Matchs m WHERE m.statut = :statut")})
public class Matchs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATCH")
    private Integer idMatch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_MATCH")
    @Temporal(TemporalType.DATE)
    private Date dateMatch;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ARBITRE")
    private String arbitre;
    @Column(name = "BUTS_DOMICILE")
    private Integer butsDomicile;
    @Column(name = "BUTS_EXTERIEUR")
    private Integer butsExterieur;
    @Size(max = 15)
    @Column(name = "STATUT")
    private String statut;
    @JoinColumn(name = "ID_EQUIPE_DOMICILE", referencedColumnName = "ID_EQUIPE")
    @ManyToOne
    private Equipes idEquipeDomicile;
    @JoinColumn(name = "ID_EQUIPE_EXTERIEUR", referencedColumnName = "ID_EQUIPE")
    @ManyToOne
    private Equipes idEquipeExterieur;
    @JoinColumn(name = "ID_SAISON", referencedColumnName = "ID_SAISON")
    @ManyToOne
    private Saison idSaison;
    @JoinColumn(name = "ID_EQUIPE_VAINQUEUR", referencedColumnName = "ID_EQUIPE")
    @ManyToOne
    private Equipes idEquipeVainqueur;

    public Matchs() {
    }

    public Matchs(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Matchs(Integer idMatch, Date dateMatch, String arbitre) {
        this.idMatch = idMatch;
        this.dateMatch = dateMatch;
        this.arbitre = arbitre;
    }

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public String getArbitre() {
        return arbitre;
    }

    public void setArbitre(String arbitre) {
        this.arbitre = arbitre;
    }

    public Integer getButsDomicile() {
        return butsDomicile;
    }

    public void setButsDomicile(Integer butsDomicile) {
        this.butsDomicile = butsDomicile;
    }

    public Integer getButsExterieur() {
        return butsExterieur;
    }

    public void setButsExterieur(Integer butsExterieur) {
        this.butsExterieur = butsExterieur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Equipes getIdEquipeDomicile() {
        return idEquipeDomicile;
    }

    public void setIdEquipeDomicile(Equipes idEquipeDomicile) {
        this.idEquipeDomicile = idEquipeDomicile;
    }

    public Equipes getIdEquipeExterieur() {
        return idEquipeExterieur;
    }

    public void setIdEquipeExterieur(Equipes idEquipeExterieur) {
        this.idEquipeExterieur = idEquipeExterieur;
    }

    public Saison getIdSaison() {
        return idSaison;
    }

    public void setIdSaison(Saison idSaison) {
        this.idSaison = idSaison;
    }

    public Equipes getIdEquipeVainqueur() {
        return idEquipeVainqueur;
    }

    public void setIdEquipeVainqueur(Equipes idEquipeVainqueur) {
        this.idEquipeVainqueur = idEquipeVainqueur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatch != null ? idMatch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matchs)) {
            return false;
        }
        Matchs other = (Matchs) object;
        if ((this.idMatch == null && other.idMatch != null) || (this.idMatch != null && !this.idMatch.equals(other.idMatch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Matchs[ idMatch=" + idMatch + " ]";
    }
    
}
