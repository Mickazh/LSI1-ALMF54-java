package entity;


public class Programmeur {
    private String nom;
    private String prenom;
    private int anneeNaissance;
    private int salaire;
    private int prime;
    private String pseudo;
    
    /**
     * @param nom
     * @param prenom
     * @param anneeNaissance
     * @param salaire
     * @param prime
     * @param pseudo
     */
    public Programmeur(String nom, String prenom, int anneeNaissance, int salaire, int prime, String pseudo) {
      super();
      this.nom = nom;
      this.prenom = prenom;
      this.anneeNaissance = anneeNaissance;
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

    
    
}
