package entity;

import java.util.Date;
import java.util.List;

public record Projet(
    String intitule,
    Date dateDebut,
    Date dateFin,
    Etat etat,
    List<Programmeur> programmeurs) {
}
