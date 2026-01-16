package entity;

import java.util.Date;
import java.util.List;

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
     * @param intitule
     * @param dateDebut
     * @param dateFin
     * @param etat
     * @param programmeurs
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
     * 
     * @param id
     * @param intitule
     * @param dateDebut
     * @param dateFin
     * @param etat
     * @param programmeurs
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
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }
    /**
     * @param intitule the intitule to set
     */
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    /**
     * @return the dateDebut
     */
    public Date getDateDebut() {
        return dateDebut;
    }
    /**
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    /**
     * @return the dateFin
     */
    public Date getDateFin() {
        return dateFin;
    }
    /**
     * @param dateFin the dateFin to set
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    /**
     * @return the etat
     */
    public Etat getEtat() {
        return etat;
    }
    /**
     * @param etat the etat to set
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    /**
     * @return the programmeurs
     */
    public List<Programmeur> getProgrammeurs() {
        return programmeurs;
    }
    /**
     * @param programmeurs the programmeurs to set
     */
    public void setProgrammeurs(List<Programmeur> programmeurs) {
        this.programmeurs = programmeurs;
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}