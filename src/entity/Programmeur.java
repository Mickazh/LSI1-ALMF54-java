package entity;


public record Programmeur(
    String nom,
    String prenom,
    int anneeNaissance,
    int salaire,
    int prime,
    String pseudo
) {
}
