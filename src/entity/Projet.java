package entity;

import java.util.Date;
import java.util.List;

/**
 * Représente un projet dans le système de gestion.
 * Un projet possède un intitulé, des dates de début et fin, un état et une liste de programmeurs affectés.
 */
public class Projet {

    public static final String dateDebutMin = "01/01/1900";
    public static final String dateDebutMax = "01/01/2100";

    private int id;
    private String intitule;
    private Date dateDebut;
    private Date dateFin;
    private Etat etat;
    private List<Programmeur> programmeurs;

    /**
     * Constructeur sans identifiant car auto incrémenté dans la base.
     * @param intitule L'intitulé du projet
     * @param dateDebut La date de début du projet
     * @param dateFin La date de fin du projet
     * @param etat L'état du projet
     * @param programmeurs La liste des programmeurs affectés au projet
     */
    public Projet(String intitule, Date dateDebut, Date dateFin, Etat etat, List<Programmeur> programmeurs) {
        super();
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.programmeurs = programmeurs;
    }

    /**
     * Constructeur avec identifiant.
     * Utilisé lors du chargement d'un projet existant depuis la base de données.
     * @param id L'identifiant unique du projet
     * @param intitule L'intitulé du projet
     * @param dateDebut La date de début du projet
     * @param dateFin La date de fin du projet
     * @param etat L'état du projet
     * @param programmeurs La liste des programmeurs affectés au projet
     */
    public Projet(int id, String intitule, Date dateDebut, Date dateFin, Etat etat, List<Programmeur> programmeurs) {
        this.id = id;
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.programmeurs = programmeurs;
    }




    /**
     * Retourne l'intitulé du projet.
     * @return L'intitulé du projet
     */
    public String getIntitule() {
        return intitule;
    }
    /**
     * Définit l'intitulé du projet.
     * @param intitule Le nouvel intitulé du projet
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    /**
     * Retourne la date de début du projet.
     * @return La date de début du projet
     */
    public Date getDateDebut() {
        return dateDebut;
    }
    /**
     * Définit la date de début du projet.
     * @param dateDebut La nouvelle date de début du projet
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    /**
     * Retourne la date de fin du projet.
     * @return La date de fin du projet
     */
    public Date getDateFin() {
        return dateFin;
    }
    /**
     * Définit la date de fin du projet.
     * @param dateFin La nouvelle date de fin du projet
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    /**
     * Retourne l'état du projet.
     * @return L'état du projet
     */
    public Etat getEtat() {
        return etat;
    }
    /**
     * Définit l'état du projet.
     * @param etat Le nouvel état du projet
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    /**
     * Retourne la liste des programmeurs affectés au projet.
     * @return La liste des programmeurs du projet
     */
    public List<Programmeur> getProgrammeurs() {
        return programmeurs;
    }
    /**
     * Définit la liste des programmeurs affectés au projet.
     * @param programmeurs La nouvelle liste des programmeurs du projet
     */
    public void setProgrammeurs(List<Programmeur> programmeurs) {
        this.programmeurs = programmeurs;
    }


    /**
     * Retourne l'identifiant du projet.
     * @return L'identifiant du projet
     */
    public int getId() {
        return id;
    }
    /**
     * Définit l'identifiant du projet.
     * @param id Le nouvel identifiant du projet
     */
    public void setId(int id) {
        this.id = id;
    }
}