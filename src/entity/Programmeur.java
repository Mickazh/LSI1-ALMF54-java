package entity;

import java.time.Year;

/**
 * Représente un programmeur dans le système de gestion de projets.
 * Un programmeur possède des informations personnelles et professionnelles.
 */
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
     * Constructeur par défaut.
     */
    public Programmeur() {}
    
    /**
     * Constructeur avec tous les paramètres.
     * @param id L'identifiant unique du programmeur
     * @param nom Le nom du programmeur
     * @param prenom Le prénom du programmeur
     * @param adresse L'adresse du programmeur
     * @param anneeNaissance L'année de naissance du programmeur
     * @param hobby Le hobby du programmeur
     * @param responsable Le responsable du programmeur
     * @param salaire Le salaire du programmeur
     * @param prime La prime du programmeur
     * @param pseudo Le pseudo du programmeur
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
     * Retourne le nom du programmeur.
     * @return Le nom du programmeur
     */
    public String getNom() {
      return nom;
    }


    /**
     * Définit le nom du programmeur.
     * @param nom Le nouveau nom du programmeur
     */
    public void setNom(String nom) {
      this.nom = nom;
    }


    /**
     * Retourne le prénom du programmeur.
     * @return Le prénom du programmeur
     */
    public String getPrenom() {
      return prenom;
    }


    /**
     * Définit le prénom du programmeur.
     * @param prenom Le nouveau prénom du programmeur
     */
    public void setPrenom(String prenom) {
      this.prenom = prenom;
    }


    /**
     * Retourne l'année de naissance du programmeur.
     * @return L'année de naissance du programmeur
     */
    public int getAnneeNaissance() {
      return anneeNaissance;
    }


    /**
     * Définit l'année de naissance du programmeur.
     * @param anneeNaissance La nouvelle année de naissance du programmeur
     */
    public void setAnneeNaissance(int anneeNaissance) {
      this.anneeNaissance = anneeNaissance;
    }


    /**
     * Retourne le salaire du programmeur.
     * @return Le salaire du programmeur
     */
    public int getSalaire() {
      return salaire;
    }


    /**
     * Définit le salaire du programmeur.
     * @param salaire Le nouveau salaire du programmeur
     */
    public void setSalaire(int salaire) {
      this.salaire = salaire;
    }


    /**
     * Retourne la prime du programmeur.
     * @return La prime du programmeur
     */
    public int getPrime() {
      return prime;
    }


    /**
     * Définit la prime du programmeur.
     * @param prime La nouvelle prime du programmeur
     */
    public void setPrime(int prime) {
      this.prime = prime;
    }


    /**
     * Retourne le pseudo du programmeur.
     * @return Le pseudo du programmeur
     */
    public String getPseudo() {
      return pseudo;
    }


    /**
     * Définit le pseudo du programmeur.
     * @param pseudo Le nouveau pseudo du programmeur
     */
    public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
    }


    /**
     * Retourne l'identifiant du programmeur.
     * @return L'identifiant du programmeur
     */
    public int getId() {
      return id;
    }


    /**
     * Définit l'identifiant du programmeur.
     * @param id Le nouvel identifiant du programmeur
     */
    public void setId(int id) {
      this.id = id;
    }

    /**
     * Retourne le hobby du programmeur.
     * @return Le hobby du programmeur
     */
    public String getHobby() {
      return hobby;
    }

    /**
     * Définit le hobby du programmeur.
     * @param hobby Le nouveau hobby du programmeur
     */
    public void setHobby(String hobby) {
      this.hobby = hobby;
    }

    /**
     * Retourne le responsable du programmeur.
     * @return Le responsable du programmeur
     */
    public String getResponsable() {
      return responsable;
    }

    /**
     * Définit le responsable du programmeur.
     * @param responsable Le nouveau responsable du programmeur
     */
    public void setResponsable(String responsable) {
      this.responsable = responsable;
    }

    /**
     * Retourne l'adresse du programmeur.
     * @return L'adresse du programmeur
     */
    public String getAdresse() {
      return adresse;
    }

    /**
     * Définit l'adresse du programmeur.
     * @param adresse La nouvelle adresse du programmeur
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
