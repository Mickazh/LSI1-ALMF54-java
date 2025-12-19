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
          afficherProgrammeur();
          break;
        case 3:
          supprimerProgrammeur();
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
  }
}
