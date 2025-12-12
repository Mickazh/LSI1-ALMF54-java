package database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Programmeur;
import entity.Projet;


public class ActionBDDImpl {
  private static Connection connection;

  public static void main(String[] args) {
    connect();
    List<Programmeur> programmeurs = getProgrammeurs();
    for (Programmeur programmeur : programmeurs) {
      System.out.println(programmeur);
    }
  }

  public static void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdtpjava", "root", "root");
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

  }

  public static List<Programmeur> getProgrammeurs() {
    // Create statement
    try {
      Statement statement = connection.createStatement();
      // Execute query
      ResultSet resultSet = statement.executeQuery("SELECT * FROM programmeur");

      List<Programmeur> programmeurs = new ArrayList<>();

      while (resultSet.next()) {
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        int ANNAISSANCE = resultSet.getInt("ANNAISSANCE");
        int salaire = resultSet.getInt("salaire");
        int prime = resultSet.getInt("prime");
        String pseudo = resultSet.getString("pseudo");
        Programmeur programmeur = new Programmeur(nom, prenom, ANNAISSANCE, salaire, prime, pseudo);
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

  public static boolean deleteProgrammeur(int id) {
    return true;
  }

  public static boolean addProgrammeur(Programmeur programmeur) {
    return true;
  }

  public static boolean updateProgrammeur(int id, Programmeur programmeur) {
    return true;
  }

  public static List<Projet> getProjets() {
    return null;
  }

  public static List<Programmeur> getProgrammeursFromProjet(int idProjet) {
    return null;
  }
}
