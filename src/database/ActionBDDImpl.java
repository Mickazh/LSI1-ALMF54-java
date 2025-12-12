package database;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Etat;
import entity.Programmeur;
import entity.Projet;


public class ActionBDDImpl {
  private Connection connection;

  public ActionBDDImpl() {
    connect();
  }

  public void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

      // TODO sortir vers des fichiers de config
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdtpjava", "root", "root");
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
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
    int ANNAISSANCE = resultSet.getInt("ANNAISSANCE");
    String hobby = resultSet.getString("hobby");
    String responsable = resultSet.getString("responsable");
    int salaire = resultSet.getInt("salaire");
    int prime = resultSet.getInt("prime");
    String pseudo = resultSet.getString("pseudo");
    Programmeur programmeur = new Programmeur(id, nom, prenom, ANNAISSANCE, hobby, responsable, salaire, prime, pseudo);
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

  public boolean addProgrammeur(Programmeur programmeur) {
    return true;
  }

  public boolean updateProgrammeur(int id, Programmeur programmeur) {
    return true;
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
              Projet projet = new Projet(intitule, dateDebut, dateFin, etat, programmeurs);
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
