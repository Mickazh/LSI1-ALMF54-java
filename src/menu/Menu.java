package menu;

import java.util.List;
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
          afficherProgrammeur();
          break;
        case 3:
          supprimerProgrammeur();
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

  private void supprimerProgrammeur() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'supprimerProrgammeur'");
  }

  private void afficherProgrammeur() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProgrammeur'");

  }

  private void afficherProgrammeurs() {
    List<Programmeur> programmeurs = this.actionBDDImpl.getProgrammeurs();
    for (Programmeur programmeur : programmeurs) {
      System.out.println(programmeur);
    }
    throw new UnsupportedOperationException("Unimplemented method 'afficherProgrammeurs'");
  }
}
