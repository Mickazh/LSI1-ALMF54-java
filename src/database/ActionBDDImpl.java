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

/**
 * Implémentation de l'interface ActionBDD pour les opérations sur la base de données.
 * Cette classe gère la connexion à la base de données MySQL et fournit les méthodes
 * CRUD pour les programmeurs et les projets.
 */
public class ActionBDDImpl implements ActionBDD {
  private Connection connection;

  public ActionBDDImpl() {
    connect();
  }

  /**
   * Connexion à la base de données
   */
  public void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String dbUrl ="jdbc:mysql://localhost:3306/bdtpjava";
      connection = DriverManager.getConnection(dbUrl, "root", "root");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Récupère l'ensemble des programmeurs
   * @return La liste de programmeurs
   */
  public List<Programmeur> getProgrammeurs() {
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT id, nom, prenom, adresse, ANNAISSANCE, hobby, responsable, salaire, prime, pseudo FROM programmeur");

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

  /**
   * Créer un programmeur grâce au resultat d'une requête
   * @param resultSet Le résultat
   * @return Le pogrammeur à partir du resultSet
   * @throws SQLException 
   */
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

  /**
   * Obtention d'un programmeur selon son id
   * @param idProgrammeur L'id du programmeur
   * @return Le programmeur s'il existe
   */
  public Optional<Programmeur> getProgrammeur(int idProgrammeur) {
    try {
      PreparedStatement statement = connection.prepareStatement("SELECT id, nom, prenom, adresse, ANNAISSANCE, hobby, responsable, salaire, prime, pseudo FROM programmeur WHERE id = ?");
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

  /**
   * @param idProgrammeur L'id du programmeur à supprimer
   * @return true s'il a été supprimé, false sinon
   */
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
      statement.setInt(10, id);

      int affectedRows = statement.executeUpdate();
      statement.close();

      return affectedRows == 1;

    } catch (SQLException e) {
      System.err.println("Erreur lors de la mise à jour du programmeur : " + e.getMessage());
      return false;
    }
  }

  /**
   * Récupère tous les projets de la base de données.
   * @return La liste de tous les projets avec leurs programmeurs associés
   */
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

  /**
   * Récupère la liste des programmeurs affectés à un projet spécifique.
   * @param idProjet L'identifiant du projet
   * @return La liste des programmeurs du projet
   */
  public List<Programmeur> getProgrammeursFromProjet(int idProjet) {
    try {
      String query = "SELECT id, nom, prenom, adresse, ANNAISSANCE, hobby, responsable, salaire, prime, pseudo " + 
                     "FROM programmeur " +
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

  /**
   * Ajouter un projet
   * @param projet le projet à ajouter
   * @return true si l'ajout a réussi, false sinon
   */
  public boolean addProjet(Projet projet) {
    try {
      // Commencer une transaction
      connection.setAutoCommit(false);

      // Insérer le projet
      PreparedStatement projetStatement = connection.prepareStatement(
          "INSERT INTO projet (intitule, dateDebut, dateFin, etat) VALUES (?, ?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS
      );

      projetStatement.setString(1, projet.getIntitule());
      projetStatement.setDate(2, new java.sql.Date(projet.getDateDebut().getTime()));
      projetStatement.setDate(3, new java.sql.Date(projet.getDateFin().getTime()));
      projetStatement.setString(4, projet.getEtat().toString());

      int affectedRows = projetStatement.executeUpdate();

      if (affectedRows == 0) {
        connection.rollback();
        return false;
      }

      // Récupérer l'ID généré du projet
      ResultSet generatedKeys = projetStatement.getGeneratedKeys();
      int projetId = 0;
      if (generatedKeys.next()) {
        projetId = generatedKeys.getInt(1);
      } else {
        connection.rollback();
        return false;
      }

      // Insérer les relations programmeur-projet
      if (projet.getProgrammeurs() != null && !projet.getProgrammeurs().isEmpty()) {
        PreparedStatement relationStatement = connection.prepareStatement(
            "INSERT INTO programmeur_projet (idProgrammeur, idProjet) VALUES (?, ?)"
        );

        for (Programmeur programmeur : projet.getProgrammeurs()) {
          relationStatement.setInt(1, programmeur.getId());
          relationStatement.setInt(2, projetId);
          relationStatement.addBatch();
        }

        int[] batchResults = relationStatement.executeBatch();
        for (int result : batchResults) {
          if (result == PreparedStatement.EXECUTE_FAILED) {
            connection.rollback();
            return false;
          }
        }

        relationStatement.close();
      }

      connection.commit();
      projetStatement.close();
      generatedKeys.close();

      return true;

    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException rollbackEx) {
        System.err.println("Erreur lors du rollback : " + rollbackEx.getMessage());
      }
      System.err.println("Erreur lors de l'ajout du projet : " + e.getMessage());
      return false;
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        System.err.println("Erreur lors de la remise en auto-commit : " + e.getMessage());
      }
    }
  }

  @Override
  public boolean updateProjet(int idProjet, Projet projet) {
    try {
      // Commencer une transaction
      connection.setAutoCommit(false);

      // Mettre à jour le projet
      PreparedStatement projetStatement = connection.prepareStatement(
          "UPDATE projet SET intitule = ?, dateDebut = ?, dateFin = ?, etat = ? WHERE id = ?"
      );

      projetStatement.setString(1, projet.getIntitule());
      projetStatement.setDate(2, new java.sql.Date(projet.getDateDebut().getTime()));
      projetStatement.setDate(3, new java.sql.Date(projet.getDateFin().getTime()));
      projetStatement.setString(4, projet.getEtat().toString());
      projetStatement.setInt(5, idProjet);

      int affectedRows = projetStatement.executeUpdate();

      if (affectedRows == 0) {
        connection.rollback();
        return false;
      }

      // Supprimer les anciennes relations programmeur-projet
      PreparedStatement deleteRelationStatement = connection.prepareStatement(
          "DELETE FROM programmeur_projet WHERE idProjet = ?"
      );
      deleteRelationStatement.setInt(1, idProjet);
      deleteRelationStatement.executeUpdate();
      deleteRelationStatement.close();

      // Insérer les nouvelles relations programmeur-projet
      if (projet.getProgrammeurs() != null && !projet.getProgrammeurs().isEmpty()) {
        PreparedStatement insertRelationStatement = connection.prepareStatement(
            "INSERT INTO programmeur_projet (idProgrammeur, idProjet) VALUES (?, ?)"
        );

        for (Programmeur programmeur : projet.getProgrammeurs()) {
          insertRelationStatement.setInt(1, programmeur.getId());
          insertRelationStatement.setInt(2, idProjet);
          insertRelationStatement.addBatch();
        }

        int[] batchResults = insertRelationStatement.executeBatch();
        for (int result : batchResults) {
          if (result == PreparedStatement.EXECUTE_FAILED) {
            connection.rollback();
            return false;
          }
        }

        insertRelationStatement.close();
      }

      connection.commit();
      projetStatement.close();

      return true;

    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException rollbackEx) {
        System.err.println("Erreur lors du rollback : " + rollbackEx.getMessage());
      }
      System.err.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
      return false;
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException e) {
        System.err.println("Erreur lors de la remise en auto-commit : " + e.getMessage());
      }
    }
  }

}
