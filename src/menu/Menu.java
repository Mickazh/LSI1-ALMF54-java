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
          ajouterProgrammeur();
          break;
        case 5:
          modifierSalaire();
          break;
        case 6:
          afficherProjets();
          break;
        case 7:
          afficherProgrammeursDUnProjet();
          break;
        case 8:
          scanner.close();
          return;
        default:
          break;
      }
      
    } while (true);
  }

  private void afficherProgrammeursDUnProjet() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProgrammeursDUnProjet'");
  }

  private void afficherProjets() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProjets'");

  }

  private void modifierSalaire() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'modifierSalaire'");
  }

  private void ajouterProgrammeur() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ajouterProgrammeur'");
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
