package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Etat;
import entity.Programmeur;
import entity.Projet;


public class ActionBDDImpl implements ActionBDD {
  private Connection connection;

  public ActionBDDImpl() {
    connect();
  }

  public void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String dbUrl ="jdbc:mysql://localhost:3306/bdtpjava";
      connection = DriverManager.getConnection(dbUrl, "root", "root");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public List<Programmeur> getProgrammeurs() {
    // Create statement
    try {
      Statement statement = connection.createStatement();
      // Execute query
      ResultSet resultSet = statement.executeQuery("SELECT * FROM programmeur");

      List<Programmeur> programmeurs = new ArrayList<>();

      while (resultSet.next()) {
        Programmeur programmeur = getProgrammeurFromResultSet(resultSet);
        programmeurs.add(programmeur);
      }
      // Close resources
      resultSet.close();
      statement.close();
      return programmeurs;
    } catch (SQLException e) {
      e.printStackTrace();
      return List.of();
    }
  }

  private Programmeur getProgrammeurFromResultSet(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt("id");
    String nom = resultSet.getString("nom");
    String prenom = resultSet.getString("prenom");
    String adresse = resultSet.getString("adresse");
    int ANNAISSANCE = resultSet.getInt("ANNAISSANCE");
    String hobby = resultSet.getString("hobby");
    String responsable = resultSet.getString("responsable");
    int salaire = resultSet.getInt("salaire");
    int prime = resultSet.getInt("prime");
    String pseudo = resultSet.getString("pseudo");
    Programmeur programmeur = new Programmeur(id, nom, prenom, adresse, ANNAISSANCE, hobby, responsable, salaire, prime, pseudo);
    return programmeur;
  }

  public Optional<Programmeur> getProgrammeur(int idProgrammeur) {
    try {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM programmeur WHERE id = ?");
      statement.setInt(1, idProgrammeur);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return Optional.of(getProgrammeurFromResultSet(resultSet));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();

  }

  public boolean deleteProgrammeur(int idProgrammeur) {
    try {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM programmeur WHERE id = ?");
      statement.setInt(1, idProgrammeur);
      int affectedRow = statement.executeUpdate();

      return affectedRow == 1;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Ajouter un programmeur
   * @param programmeur le programmeur
   * @return true si la requête s'est bien passée, false sinon
   */
  public boolean addProgrammeur(Programmeur programmeur) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO programmeur (nom, prenom, adresse, ANNAISSANCE, hobby, responsable, salaire, prime, pseudo) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
      );

      statement.setString(1, programmeur.getNom());
      statement.setString(2, programmeur.getPrenom());
      statement.setString(3, programmeur.getAdresse());
      statement.setInt(4, programmeur.getAnneeNaissance());
      statement.setString(5, programmeur.getHobby());
      statement.setString(6, programmeur.getResponsable());
      statement.setInt(7, programmeur.getSalaire());
      statement.setInt(8, programmeur.getPrime());
      statement.setString(9, programmeur.getPseudo());

      int affectedRows = statement.executeUpdate();
      statement.close();

      return affectedRows == 1;

    } catch (SQLException e) {
      System.err.println("Erreur lors de l'ajout du programmeur : " + e.getMessage());
      return false;
    }
  }

  /**
   * Mettre à jour un programmeur
   * @param id l'id du programmeur à mettre à jour
   * @param programmeur le programmeur contenant les modifications
   * @return true si la requête s'est bien passée, false sinon
   */
  public boolean updateProgrammeur(int id, Programmeur programmeur) {
    try {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE programmeur SET nom = ?, prenom = ?, adresse = ?, ANNAISSANCE = ?, " +
          "hobby = ?, responsable = ?, salaire = ?, prime = ?, pseudo = ? " +
          "WHERE id = ?"
      );

      statement.setString(1, programmeur.getNom());
      statement.setString(2, programmeur.getPrenom());
      statement.setString(3, programmeur.getAdresse());
      statement.setInt(4, programmeur.getAnneeNaissance());
      statement.setString(5, programmeur.getHobby());
      statement.setString(6, programmeur.getResponsable());
      statement.setInt(7, programmeur.getSalaire());
      statement.setInt(8, programmeur.getPrime());
      statement.setString(9, programmeur.getPseudo());
      statement.setInt(10, id); // Use the method parameter id, not programmeur.getId()

      int affectedRows = statement.executeUpdate();
      statement.close();

      return affectedRows == 1;

    } catch (SQLException e) {
      System.err.println("Erreur lors de la mise à jour du programmeur : " + e.getMessage());
      return false;
    }
  }

  public List<Projet> getProjets() {
      try {
          String query = "SELECT id, intitule, dateDebut, dateFin, etat FROM projet";
          PreparedStatement statement = connection.prepareStatement(query);
          ResultSet resultat = statement.executeQuery();
          List<Projet> projets = new ArrayList<>();
          while (resultat.next()) {
              int id = resultat.getInt("id");
              String intitule = resultat.getString("intitule");
              Date dateDebut = resultat.getDate("dateDebut");
              Date dateFin = resultat.getDate("dateFin");
              Etat etat = Etat.valueOf(resultat.getString("etat"));
              List<Programmeur> programmeurs = getProgrammeursFromProjet(id);
              Projet projet = new Projet(id, intitule, dateDebut, dateFin, etat, programmeurs);
              projets.add(projet);
          }
          resultat.close();
          statement.close();
          return projets;
      } catch (SQLException e) {
          System.out.println("Erreur lors de la récupération des projets : " + e.getMessage());
          return List.of();
      }
  }

  public List<Programmeur> getProgrammeursFromProjet(int idProjet) {
    try {
      String query = "SELECT * FROM programmeur " +
                     "INNER JOIN programmeur_projet ON programmeur.id = programmeur_projet.idProgrammeur " +
                     "WHERE programmeur_projet.idProjet = ?";
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, idProjet);
      ResultSet resultSet = statement.executeQuery();

      List<Programmeur> programmeurs = new ArrayList<>();
      while (resultSet.next()) {
        Programmeur programmeur = getProgrammeurFromResultSet(resultSet);
        programmeurs.add(programmeur);
      }

      resultSet.close();
      statement.close();
      return programmeurs;
    } catch (SQLException e) {
      System.out.println("Erreur lors de la récupération des programmeurs du projet : " + e.getMessage());
      return List.of();
    }
  }
}
