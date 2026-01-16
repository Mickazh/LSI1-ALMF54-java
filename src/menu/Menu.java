package menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import database.ActionBDDImpl;
import entity.Programmeur;

public class Menu {

  private ActionBDDImpl actionBDDImpl;

  public Menu() {
    this.actionBDDImpl = new ActionBDDImpl();
  }

  /**
   * Boucle principale permettant d'échanger avec l'utilisateur
   */
  public void start() {
    int choix;
    Scanner scanner = new Scanner(System.in);
    do {
      afficherMenu();
      String choixStr = scanner.nextLine().trim();
      try {
        choix = Integer.parseInt(choixStr);
      } catch (NumberFormatException e) {
        System.out.println("Choix invalide. Veuillez réessayer.");
        continue;
      }
      switch (choix) {
        case 1:
          afficherProgrammeurs();
          break;
        case 2:
          afficherProgrammeur(scanner);
          break;
        case 3:
          supprimerProgrammeur(scanner);
          break;
        case 4: 
          ajouterProgrammeur(scanner);
          break;
        case 5: 
          modifierSalaire(scanner);
          break;
        case 6:
          afficherProjets();
          break;
        case 7:
          afficherProgrammeursDUnProjet(scanner);
          break;
        case 8:
          scanner.close();
          return;
        default:
          System.out.println("Choix invalide. Veuillez réessayer.");
          break;
      }
      
    } while (true);
  }

  /**
   * Affichage du menu principal
   */
  private void afficherMenu() {
    System.out.println("\n************** MENU PRINCIPAL **************");
    System.out.println("1. Afficher tous les programmeurs");
    System.out.println("2. Afficher un programmeur");
    System.out.println("3. Supprimer un programmeur");
    System.out.println("4. Ajouter un programmeur");
    System.out.println("5. Modifier le salaire d'un programmeur");
    System.out.println("6. Afficher tous les projets");
    System.out.println("7. Afficher les programmeurs d'un projet");
    System.out.println("8. Quitter");
    System.out.print("Votre choix : ");
  }

  /**
   * Permet d'afficher les programmeurs lié à un projet
   * @param scanner Le scanner permettant de récupérer les inputs utilisateur
   */
  private void afficherProgrammeursDUnProjet(Scanner scanner) {
    System.out.println("\nAfficher les programmeurs d'un projet");
    System.out.print("Entrez l'ID du projet : ");
    String idStr = scanner.nextLine().trim();
    int idProjet;
    try {
      idProjet = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
      System.out.println("ID invalide.");
      return;
    }

    List<Programmeur> programmeurs = this.actionBDDImpl.getProgrammeursFromProjet(idProjet);

    if (programmeurs.isEmpty()) {
      System.out.println("Aucun programmeur trouvé pour ce projet.");
    } else {
      System.out.println("\n Programmeurs du projet " + idProjet + " :");
      for (Programmeur programmeur : programmeurs) {
        System.out.println(programmeur);
      }
    }
  }

  /**
   * Affiche l'ensemble des projets
   */
  private void afficherProjets() {
    List<entity.Projet> projets = this.actionBDDImpl.getProjets();
    if (projets.isEmpty()) {
      System.out.println("Aucun projet trouvé.");
    } else {
      System.out.println("\nListe des projets");
      for (entity.Projet projet : projets) {
        System.out.println("ID: " + projet.getId() + " - " + projet.getIntitule() +
                         " (État: " + projet.getEtat() + ")");
        System.out.println("  Début: " + projet.getDateDebut() + " - Fin: " + projet.getDateFin());
        System.out.println("  Nombre de programmeurs: " + projet.getProgrammeurs().size());
        System.out.println();
      }
    }
  }

  /**
   * Modifier le salaire d'un programmeur
   * @param in le scanner
   * @return true si la modification a réussie, false sinon
   */
  private boolean modifierSalaire(Scanner in) {
    final int MAX_TRY_COUNT = 3;

    Optional<Programmeur> programmeur;
    int tryCount = 1;
    try {
      do {
        System.out.println("Id du programmeur: ");
        String idStr = in.nextLine().trim();
        int id = Integer.parseInt(idStr);

        programmeur = actionBDDImpl.getProgrammeur(id);
        if (tryCount++ >= MAX_TRY_COUNT) {
          System.out.println("Trop de tentative");
          return false;
        }
      } while (programmeur.isEmpty());

        Programmeur p = programmeur.get();

        System.out.println("Nouveau salaire du programmeur: ");
        String salaireStr = in.nextLine().trim();
        int salaire = Integer.parseInt(salaireStr);

        p.setSalaire(salaire);

        return actionBDDImpl.updateProgrammeur(p.getId(), p);
    } catch (Exception e) {
      return false;
    }

  }

  /**
   * Ajouter un programmeur
   * @param in le scanner
   * @return true si l'ajout a réussi, false sinon
   */
  private boolean ajouterProgrammeur(Scanner in) {
    Programmeur p = new Programmeur();

    try {
      System.out.println("Nom du programmeur: ");
      p.setNom(in.nextLine().trim());
      System.out.println("Prénom du programmeur: ");
      p.setPrenom(in.nextLine().trim());
      System.out.println("Adresse du programmeur: ");
      p.setAdresse(in.nextLine().trim());
      System.out.println("Année de naissance du programmeur: ");
      p.setAnneeNaissance(Integer.parseInt(in.nextLine().trim()));
      System.out.println("Hobby du programmeur: ");
      p.setHobby(in.nextLine().trim());
      System.out.println("Responsable du programmeur: ");
      p.setResponsable(in.nextLine().trim());
      System.out.println("Salaire du programmeur: ");
      p.setSalaire(Integer.parseInt(in.nextLine().trim()));
      System.out.println("Prime du programmeur: ");
      p.setPrime(Integer.parseInt(in.nextLine().trim()));
      System.out.println("Pseudo du programmeur: ");
      p.setPseudo(in.nextLine().trim());

      
      if (actionBDDImpl.addProgrammeur(p)) {
        System.out.println("AJOUT REUSSI !");
        return true;
      }
      return false;

    } catch (NumberFormatException numberFormatException) {
      System.err.println("Saisie incorrect");
        return false;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Supprime un programmeur selon son id
   * @param scanner Le scanner permettant de récupérer les inputs utilisateur
   */
  private void supprimerProgrammeur(Scanner scanner) {
    while (true) {
      System.out.print("Entrez l'id du programmeur à supprimer : ");
      String idStr = scanner.nextLine().trim();
      try {
        int idProgrammeur = Integer.parseInt(idStr);
        boolean isProgmmeurSupprime = actionBDDImpl.deleteProgrammeur(idProgrammeur);
        if (isProgmmeurSupprime) {
          System.out.println("Programmeur supprimé avec succès.");
          break;
        } else {
          System.out.println("Supression KO. Saisissez à nouveau l'id : ");
        }
      } catch (NumberFormatException e) {
        System.out.println("ID invalide. Veuillez entrer un nombre.");
      }
    }
  }

  /**
   * Affiche un programmeur selon son id
   * @param scanner Le scanner permettant de récupérer les inputs utilisateur
   */
  private void afficherProgrammeur(Scanner scanner) {
    Optional<Programmeur> optionalProgrammeur;
    while (true) {
      System.out.print("Entrez l'id du programmeur à afficher : ");
      String idStr = scanner.nextLine().trim();
      try {
        int idProgrammeur = Integer.parseInt(idStr);
        optionalProgrammeur = actionBDDImpl.getProgrammeur(idProgrammeur);
        if (optionalProgrammeur.isPresent()) {
          System.out.println(optionalProgrammeur.get());
          break;
        } else {
          System.out.println("Programmeur non trouvé.");
        }
      } catch (NumberFormatException e) {
        System.out.println("ID invalide. Veuillez entrer un nombre.");
      }
    }
  }

  /**
   * Affiche tous les programmeurs
   */
  private void afficherProgrammeurs() {
    List<Programmeur> programmeurs = this.actionBDDImpl.getProgrammeurs();
    for (Programmeur programmeur : programmeurs) {
      System.out.println(programmeur);
    }
  }
}
