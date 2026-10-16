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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "joueurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Joueurs.findAll", query = "SELECT j FROM Joueurs j")
    , @NamedQuery(name = "Joueurs.findByIdJoueur", query = "SELECT j FROM Joueurs j WHERE j.idJoueur = :idJoueur")
    , @NamedQuery(name = "Joueurs.findByPseudo", query = "SELECT j FROM Joueurs j WHERE j.pseudo = :pseudo")
    , @NamedQuery(name = "Joueurs.findByNumero", query = "SELECT j FROM Joueurs j WHERE j.numero = :numero")
    , @NamedQuery(name = "Joueurs.findByPoste", query = "SELECT j FROM Joueurs j WHERE j.poste = :poste")
    , @NamedQuery(name = "Joueurs.findByButMarques", query = "SELECT j FROM Joueurs j WHERE j.butMarques = :butMarques")})
public class Joueurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_JOUEUR")
    private Integer idJoueur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PSEUDO")
    private String pseudo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private int numero;
    @Size(max = 20)
    @Column(name = "POSTE")
    private String poste;
    @Column(name = "BUT_MARQUES")
    private Integer butMarques;
    @OneToMany(mappedBy = "idCapitaine")
    private List<Equipes> equipesList;
    @OneToMany(mappedBy = "idJoueur")
    private List<HistoriqueTransfert> historiqueTransfertList;
    @JoinColumn(name = "ID_EQUIPE", referencedColumnName = "ID_EQUIPE")
    @ManyToOne
    private Equipes idEquipe;

    public Joueurs() {
    }

    public Joueurs(Integer idJoueur) {
        this.idJoueur = idJoueur;
    }

    public Joueurs(Integer idJoueur, String pseudo, int numero) {
        this.idJoueur = idJoueur;
        this.pseudo = pseudo;
        this.numero = numero;
    }

    public Integer getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Integer idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Integer getButMarques() {
        return butMarques;
    }

    public void setButMarques(Integer butMarques) {
        this.butMarques = butMarques;
    }

    @XmlTransient
    public List<Equipes> getEquipesList() {
        return equipesList;
    }

    public void setEquipesList(List<Equipes> equipesList) {
        this.equipesList = equipesList;
    }

    @XmlTransient
    public List<HistoriqueTransfert> getHistoriqueTransfertList() {
        return historiqueTransfertList;
    }

    public void setHistoriqueTransfertList(List<HistoriqueTransfert> historiqueTransfertList) {
        this.historiqueTransfertList = historiqueTransfertList;
    }

    public Equipes getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Equipes idEquipe) {
        this.idEquipe = idEquipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJoueur != null ? idJoueur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Joueurs)) {
            return false;
        }
        Joueurs other = (Joueurs) object;
        if ((this.idJoueur == null && other.idJoueur != null) || (this.idJoueur != null && !this.idJoueur.equals(other.idJoueur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Joueurs[ idJoueur=" + idJoueur + " ]";
    }
    
}
