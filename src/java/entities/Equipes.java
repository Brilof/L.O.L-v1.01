/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BRILOF
 */
@Entity
@Table(name = "equipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipes.findAll", query = "SELECT e FROM Equipes e")
    , @NamedQuery(name = "Equipes.findByIdEquipe", query = "SELECT e FROM Equipes e WHERE e.idEquipe = :idEquipe")
    , @NamedQuery(name = "Equipes.findByNom", query = "SELECT e FROM Equipes e WHERE e.nom = :nom")
    , @NamedQuery(name = "Equipes.findByVille", query = "SELECT e FROM Equipes e WHERE e.ville = :ville")
    , @NamedQuery(name = "Equipes.findByDateCreation", query = "SELECT e FROM Equipes e WHERE e.dateCreation = :dateCreation")})
public class Equipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EQUIPE")
    private Integer idEquipe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "VILLE")
    private String ville;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @OneToMany(mappedBy = "idEquipeDomicile")
    private List<Matchs> matchsList;
    @OneToMany(mappedBy = "idEquipeExterieur")
    private List<Matchs> matchsList1;
    @OneToMany(mappedBy = "idEquipeVainqueur")
    private List<Matchs> matchsList2;
    @JoinColumn(name = "ID_CAPITAINE", referencedColumnName = "ID_JOUEUR")
    @ManyToOne
    private Joueurs idCapitaine;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "equipes")
    private Classement classement;
    @OneToMany(mappedBy = "idLastClub")
    private List<HistoriqueTransfert> historiqueTransfertList;
    @OneToMany(mappedBy = "idNewClub")
    private List<HistoriqueTransfert> historiqueTransfertList1;
    @OneToMany(mappedBy = "idEquipe")
    private List<Joueurs> joueursList;

    public Equipes() {
    }

    public Equipes(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Equipes(Integer idEquipe, String nom, String ville, Date dateCreation) {
        this.idEquipe = idEquipe;
        this.nom = nom;
        this.ville = ville;
        this.dateCreation = dateCreation;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @XmlTransient
    public List<Matchs> getMatchsList() {
        return matchsList;
    }

    public void setMatchsList(List<Matchs> matchsList) {
        this.matchsList = matchsList;
    }

    @XmlTransient
    public List<Matchs> getMatchsList1() {
        return matchsList1;
    }

    public void setMatchsList1(List<Matchs> matchsList1) {
        this.matchsList1 = matchsList1;
    }

    @XmlTransient
    public List<Matchs> getMatchsList2() {
        return matchsList2;
    }

    public void setMatchsList2(List<Matchs> matchsList2) {
        this.matchsList2 = matchsList2;
    }

    public Joueurs getIdCapitaine() {
        return idCapitaine;
    }

    public void setIdCapitaine(Joueurs idCapitaine) {
        this.idCapitaine = idCapitaine;
    }

    public Classement getClassement() {
        return classement;
    }

    public void setClassement(Classement classement) {
        this.classement = classement;
    }

    @XmlTransient
    public List<HistoriqueTransfert> getHistoriqueTransfertList() {
        return historiqueTransfertList;
    }

    public void setHistoriqueTransfertList(List<HistoriqueTransfert> historiqueTransfertList) {
        this.historiqueTransfertList = historiqueTransfertList;
    }

    @XmlTransient
    public List<HistoriqueTransfert> getHistoriqueTransfertList1() {
        return historiqueTransfertList1;
    }

    public void setHistoriqueTransfertList1(List<HistoriqueTransfert> historiqueTransfertList1) {
        this.historiqueTransfertList1 = historiqueTransfertList1;
    }

    @XmlTransient
    public List<Joueurs> getJoueursList() {
        return joueursList;
    }

    public void setJoueursList(List<Joueurs> joueursList) {
        this.joueursList = joueursList;
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
        if (!(object instanceof Equipes)) {
            return false;
        }
        Equipes other = (Equipes) object;
        if ((this.idEquipe == null && other.idEquipe != null) || (this.idEquipe != null && !this.idEquipe.equals(other.idEquipe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipes[ idEquipe=" + idEquipe + " ]";
    }
    
}
