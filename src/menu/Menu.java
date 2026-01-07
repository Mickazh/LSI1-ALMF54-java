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

  public void start() { 
    int choix;
    Scanner scanner = new Scanner(System.in);
    do {
      afficherMenu();
      choix = scanner.nextInt();
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

  private void afficherProgrammeursDUnProjet(Scanner scanner) {
    System.out.println("\nAfficher les programmeurs d'un projet");
    System.out.print("Entrez l'ID du projet : ");
    int idProjet = scanner.nextInt();

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
    
    Optional<Programmeur> programmeur;
    try {
      do {
        System.out.println("Id du programmeur: ");
        int id = in.nextInt();
        
        programmeur = actionBDDImpl.getProgrammeur(id);
      } while (programmeur.isEmpty());
      
        Programmeur p = programmeur.get();
        
        System.out.println("Nouveau salaire du programmeur: ");
        int salaire = in.nextInt();
        
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
      p.setNom(in.next());
      System.out.println("Prénom du programmeur: ");
      p.setNom(in.next());
      System.out.println("Adresse du programmeur: ");
      p.setNom(in.next());
      System.out.println("Pseudo du programmeur: ");
      p.setNom(in.next());
      System.out.println("Responsable du programmeur: ");
      p.setNom(in.next());
      System.out.println("Année de naissance du programmeur: ");
      p.setNom(in.next());
      System.out.println("Hobby du programmeur: ");
      p.setNom(in.next());
      System.out.println("Salaire du programmeur: ");
      p.setNom(in.next());
      System.out.println("Prime du programmeur: ");
      p.setNom(in.next());

      return actionBDDImpl.addProgrammeur(p);

    } catch (Exception e) {
      return false;
    }
  }

  private void supprimerProgrammeur(Scanner scanner) {
    System.out.print("Entrez l'id du programmeur à supprimer : ");
    int idProgrammeur = scanner.nextInt();
    boolean isProgmmeurSupprime = actionBDDImpl.deleteProgrammeur(idProgrammeur);
    while (!isProgmmeurSupprime) {
      System.out.print("Supression KO. Saisissez à nouveau l'id : ");
      idProgrammeur = scanner.nextInt();
      isProgmmeurSupprime = actionBDDImpl.deleteProgrammeur(idProgrammeur);
    }
  }

  private void afficherProgrammeur(Scanner scanner) {
    Optional<Programmeur> optionalProgrammeur;
    System.out.print("Entrez l'id du programmeur à afficher : ");
    int idProgrammeur = scanner.nextInt();
    optionalProgrammeur = actionBDDImpl.getProgrammeur(idProgrammeur);
    while (optionalProgrammeur.isEmpty()) {
      System.out.print("Recherche KO. Saisissez à nouveau l'id : ");
      idProgrammeur = scanner.nextInt();
      optionalProgrammeur = actionBDDImpl.getProgrammeur(idProgrammeur);
    }
    System.out.println(optionalProgrammeur.get());
  }

  private void afficherProgrammeurs() {
    List<Programmeur> programmeurs = this.actionBDDImpl.getProgrammeurs();
    for (Programmeur programmeur : programmeurs) {
      System.out.println(programmeur);
    }
  }
}
