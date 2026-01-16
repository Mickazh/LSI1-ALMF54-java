package entity;

import java.time.Year;

public class Programmeur {

  public static final int anneeNaissanceMin = 1900;
  public static final int anneeNaissanceMax = Year.now().getValue();

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private int anneeNaissance;
    private String hobby;
    private String responsable;
    private int salaire;
    private int prime;
    private String pseudo;
    
    /**
     * 
     */
    public Programmeur() {}
    
    /**
     * @param id
     * @param nom
     * @param prenom
     * @param adresse
     * @param anneeNaissance
     * @param hobby
     * @param responsable
     * @param salaire
     * @param prime
     * @param pseudo
     */
    public Programmeur(int id, String nom, String prenom, String adresse, int anneeNaissance,
                       String hobby, String responsable,
                       int salaire, int prime, String pseudo) {
      this.id = id;
      this.nom = nom;
      this.prenom = prenom;
      this.adresse = adresse;
      this.anneeNaissance = anneeNaissance;
      this.hobby = hobby;
      this.responsable = responsable;
      this.salaire = salaire;
      this.prime = prime;
      this.pseudo = pseudo;
    }


    /**
     * @return the nom
     */
    public String getNom() {
      return nom;
    }


    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
      this.nom = nom;
    }


    /**
     * @return the prenom
     */
    public String getPrenom() {
      return prenom;
    }


    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
      this.prenom = prenom;
    }


    /**
     * @return the anneeNaissance
     */
    public int getAnneeNaissance() {
      return anneeNaissance;
    }


    /**
     * @param anneeNaissance the anneeNaissance to set
     */
    public void setAnneeNaissance(int anneeNaissance) {
      this.anneeNaissance = anneeNaissance;
    }


    /**
     * @return the salaire
     */
    public int getSalaire() {
      return salaire;
    }


    /**
     * @param salaire the salaire to set
     */
    public void setSalaire(int salaire) {
      this.salaire = salaire;
    }


    /**
     * @return the prime
     */
    public int getPrime() {
      return prime;
    }


    /**
     * @param prime the prime to set
     */
    public void setPrime(int prime) {
      this.prime = prime;
    }


    /**
     * @return the pseudo
     */
    public String getPseudo() {
      return pseudo;
    }


    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
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

    /**
     * @return the hobby
     */
    public String getHobby() {
      return hobby;
    }

    /**
     * @param hobby the hobby to set
     */
    public void setHobby(String hobby) {
      this.hobby = hobby;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
      return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
      this.responsable = responsable;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
      return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
      this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Programmeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", anneeNaissance=" + anneeNaissance
                + ", hobby=" + hobby + ", responsable=" + responsable + ", salaire=" + salaire + ", prime=" + prime
                + ", pseudo=" + pseudo + "]";
    }

    
    
}
