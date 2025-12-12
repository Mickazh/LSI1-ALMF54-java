package menu;

import java.util.Scanner;

public class Menu {
  public static void start() {
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

  private static void afficherProgrammeursDUnProjet() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProgrammeursDUnProjet'");
  }

  private static void afficherProjets() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProjets'");

  }

  private static void modifierSalaire() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'modifierSalaire'");
  }

  private static void ajouterProgrammeur() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ajouterProgrammeur'");
  }

  private static void supprimerProgrammeur() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'supprimerProrgammeur'");
  }

  private static void afficherProgrammeur() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProgrammeur'");

  }

  private static void afficherProgrammeurs() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'afficherProgrammeurs'");
  }
}
