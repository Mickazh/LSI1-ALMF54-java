package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Programmeur;
import entity.Projet;


public class ActionBDDImpl {
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

  public boolean deleteProgrammeur(int id) {
    return true;
  }

  /**
   * Ajouter un programmeur
   * @param programmeur le programmeur
   * @return true si la requête s'est bien passée, false sinon
   */
  public boolean addProgrammeur(Programmeur programmeur) {
    try {
      PreparedStatement statement = connection.prepareStatement
          ("INSERT INTO programmeur VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
      statement.setInt(1, programmeur.getId());
      statement.setString(2, programmeur.getNom());
      statement.setString(3, programmeur.getPrenom());
      statement.setInt(4, programmeur.getAnneeNaissance());
      statement.setString(5, programmeur.getHobby());
      statement.setString(6, programmeur.getResponsable());
      statement.setInt(7, programmeur.getSalaire());
      statement.setInt(8, programmeur.getPrime());
      statement.setString(9, programmeur.getPseudo());
      
      statement.executeQuery();
      
      return true;
      
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Mettre à jour un programmeur
   * @param id l'id du programmeur à mettre à jour
   * @param programmeur le programmeur contenant les modification
   * @return true si la requête s'est bien passée, false sinon
   */
  public boolean updateProgrammeur(int id, Programmeur programmeur) {
    try {
      PreparedStatement statement = connection.prepareStatement
          ("UPDATE programmeur SET NOM = '?',"
                                + "PRENOM = '?',"
                                + "ANNEENAISSANCE = '?',"
                                + "HOBBY = '?',"
                                + "RESPONSABLE = '?',"
                                + "SALAIRE = '?',"
                                + "PRIME = '?',"
                                + "PSEUDO = '?'"
          + "WHERE id = ?;");
      
      statement.setString(1, programmeur.getNom());
      statement.setString(2, programmeur.getPrenom());
      statement.setInt(3, programmeur.getAnneeNaissance());
      statement.setString(4, programmeur.getHobby());
      statement.setString(5, programmeur.getResponsable());
      statement.setInt(6, programmeur.getSalaire());
      statement.setInt(7, programmeur.getPrime());
      statement.setString(8, programmeur.getPseudo());
      statement.setInt(9, programmeur.getId());
      return true;
      
    } catch (SQLException e) {
      return false;
    } 
  }

  public List<Projet> getProjets() {
    return null;
  }

  public List<Programmeur> getProgrammeursFromProjet(int idProjet) {
    return null;
  }
}
