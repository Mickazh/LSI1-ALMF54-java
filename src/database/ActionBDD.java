package database;

import java.util.List;
import java.util.Optional;

import entity.Programmeur;
import entity.Projet;

public interface ActionBDD {

    /**
     * Récupère tous les programmeurs
     * @return Liste de tous les programmeurs
     */
    List<Programmeur> getProgrammeurs();

    /**
     * Récupère un programmeur par son ID
     * @param idProgrammeur L'ID du programmeur
     * @return Optional contenant le programmeur s'il existe
     */
    Optional<Programmeur> getProgrammeur(int idProgrammeur);

    /**
     * Supprime un programmeur par son ID
     * @param idProgrammeur L'ID du programmeur à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean deleteProgrammeur(int idProgrammeur);

    /**
     * Ajoute un nouveau programmeur
     * @param programmeur Le programmeur à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    boolean addProgrammeur(Programmeur programmeur);

    /**
     * Met à jour un programmeur existant
     * @param id L'ID du programmeur à mettre à jour
     * @param programmeur Les nouvelles données du programmeur
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updateProgrammeur(int id, Programmeur programmeur);

    /**
     * Récupère tous les projets
     * @return Liste de tous les projets
     */
    List<Projet> getProjets();

    /**
     * Récupère les programmeurs travaillant sur un projet spécifique
     * @param idProjet L'ID du projet
     * @return Liste des programmeurs du projet
     */
    List<Programmeur> getProgrammeursFromProjet(int idProjet);

    /**
     * Ajoute un nouveau projet
     * @param projet Le projet à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    boolean addProjet(Projet projet);
}
