package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

import database.ActionBDDImpl;
import entity.Programmeur;

/**
 * Panneau de gestion des programmeurs
 * Permet d'afficher, ajouter, modifier et supprimer des programmeurs
 */
public class ProgrammeurPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    // Constantes pour les actions
    public static final String VIEW_ALL = "VIEW_ALL";
    public static final String VIEW_ONE = "VIEW_ONE";
    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String UPDATE_SALARY = "UPDATE_SALARY";
    public static final String UPDATE_PROG = "UPDATE_PROG";    

    private ActionBDDImpl actionBDD;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    // Panneaux pour différentes actions
    private JPanel viewAllPanel;
    private JPanel viewOnePanel;
    private JPanel addPanel;
    private JPanel deletePanel;
    private JPanel updateSalaryPanel;
    private JPanel updateProgPanel;

    /**
     * Constructeur du panneau programmeur
     * @param actionBDD Instance de la classe d'accès aux données
     */
    public ProgrammeurPanel(ActionBDDImpl actionBDD) {
        this.actionBDD = actionBDD;
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Initialise les composants du panneau
     */
    private void initComponents() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Créer les différents panneaux
        viewAllPanel = createViewAllPanel();
        viewOnePanel = createViewOnePanel();
        addPanel = createAddPanel();
        deletePanel = createDeletePanel();
        updateSalaryPanel = createUpdateSalaryPanel();
        updateProgPanel = createUpdateProgPanel();

        // Ajouter les panneaux au CardLayout
        contentPanel.add(viewAllPanel, VIEW_ALL);
        contentPanel.add(viewOnePanel, VIEW_ONE);
        contentPanel.add(addPanel, ADD);
        contentPanel.add(deletePanel, DELETE);
        contentPanel.add(updateSalaryPanel, UPDATE_SALARY);
        contentPanel.add(updateProgPanel, UPDATE_PROG);

        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Définit l'action à afficher
     * @param action Action à effectuer
     */
    public void setAction(String action) {
        cardLayout.show(contentPanel, action);
        
        // Rafraîchir les données si nécessaire
        if (VIEW_ALL.equals(action)) {
            refreshViewAllPanel();
        }
    }

    /**
     * Crée le panneau d'affichage de tous les programmeurs
     */
    private JPanel createViewAllPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Titre
        JLabel titleLabel = new JLabel("Liste de tous les programmeurs", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"ID", "Nom", "Prénom", "Année", "Hobby", "Responsable", "Salaire", "Prime", "Pseudo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bouton de rafraîchissement
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton refreshButton = new JButton("Rafraîchir");
        refreshButton.addActionListener(e -> {
            loadProgrammeursInTable(tableModel);
        });
        buttonPanel.add(refreshButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Charger les données initiales
        loadProgrammeursInTable(tableModel);

        return panel;
    }

    /**
     * Charge les programmeurs dans le tableau
     */
    private void loadProgrammeursInTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        List<Programmeur> programmeurs = actionBDD.getProgrammeurs();
        
        for (Programmeur p : programmeurs) {
            Object[] row = {
                p.getId(),
                p.getNom(),
                p.getPrenom(),
                p.getAnneeNaissance(),
                p.getHobby(),
                p.getResponsable(),
                p.getSalaire(),
                p.getPrime(),
                p.getPseudo()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Rafraîchit le panneau d'affichage de tous les programmeurs
     */
    private void refreshViewAllPanel() {
        Component[] components = viewAllPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                JTable table = (JTable) scrollPane.getViewport().getView();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                loadProgrammeursInTable(model);
                break;
            }
        }

    
    
    
    
    
    }

    /**
     * Crée le panneau d'affichage d'un programmeur
     */
    private JPanel createViewOnePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Rechercher un programmeur", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panneau central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champ ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("ID du programmeur :"), gbc);

        gbc.gridx = 1;
        JTextField idField = new JTextField(15);
        centerPanel.add(idField, gbc);

        // Zone de résultat
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Bouton de recherche
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Optional<Programmeur> optProg = actionBDD.getProgrammeur(id);
                
                if (optProg.isPresent()) {
                    Programmeur p = optProg.get();
                    resultArea.setText(
                        "ID             : " + p.getId() + "\n" +
                        "Nom            : " + p.getNom() + "\n" +
                        "Prénom         : " + p.getPrenom() + "\n" +
                        "Adresse        : " + p.getAdresse() + "\n" +
                        "Année naissance: " + p.getAnneeNaissance() + "\n" +
                        "Hobby          : " + p.getHobby() + "\n" +
                        "Responsable    : " + p.getResponsable() + "\n" +
                        "Salaire        : " + p.getSalaire() + " €\n" +
                        "Prime          : " + p.getPrime() + " €\n" +
                        "Pseudo         : " + p.getPseudo()
                    );
                } else {
                    resultArea.setText("Aucun programmeur trouvé avec l'ID " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, 
                    "Veuillez entrer un ID valide (nombre entier)", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        centerPanel.add(searchButton, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(scrollPane, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Crée le panneau d'ajout d'un programmeur
     */
    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Ajouter un nouveau programmeur", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Formulaire
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champs du formulaire
        JTextField nomField = new JTextField(20);
        JTextField prenomField = new JTextField(20);
        JTextField adresseField = new JTextField(20);
        JTextField anneeField = new JTextField(20);
        JTextField hobbyField = new JTextField(20);
        JTextField responsableField = new JTextField(20);
        JTextField salaireField = new JTextField(20);
        JTextField primeField = new JTextField(20);
        JTextField pseudoField = new JTextField(20);

        // Ajouter les champs
        int row = 0;
        addFormField(formPanel, gbc, row++, "Nom :", nomField);
        addFormField(formPanel, gbc, row++, "Prénom :", prenomField);
        addFormField(formPanel, gbc, row++, "Adresse :", adresseField);
        addFormField(formPanel, gbc, row++, "Année de naissance :", anneeField);
        addFormField(formPanel, gbc, row++, "Hobby :", hobbyField);
        addFormField(formPanel, gbc, row++, "Responsable :", responsableField);
        addFormField(formPanel, gbc, row++, "Salaire :", salaireField);
        addFormField(formPanel, gbc, row++, "Prime :", primeField);
        addFormField(formPanel, gbc, row++, "Pseudo :", pseudoField);

        panel.add(formPanel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(e -> {
            try {
                var anneeNaissance = Integer.parseInt(anneeField.getText().trim());
                if (anneeNaissance < Programmeur.anneeNaissanceMin || anneeNaissance > Programmeur.anneeNaissanceMax) {
                    throw new NumberFormatException();
                }
                Programmeur prog = new Programmeur(
                    0,
                    nomField.getText().trim(),
                    prenomField.getText().trim(),
                    adresseField.getText().trim(),
                    anneeNaissance,
                    hobbyField.getText().trim(),
                    responsableField.getText().trim(),
                    Integer.parseInt(salaireField.getText().trim()),
                    Integer.parseInt(primeField.getText().trim()),
                    pseudoField.getText().trim()
                );

                boolean success = actionBDD.addProgrammeur(prog);
                if (success) {
                    JOptionPane.showMessageDialog(panel, 
                        "Programmeur ajouté avec succès!", 
                        "Succès", 
                        JOptionPane.INFORMATION_MESSAGE);
                    // Réinitialiser les champs
                    nomField.setText("");
                    prenomField.setText("");
                    adresseField.setText("");
                    anneeField.setText("");
                    hobbyField.setText("");
                    responsableField.setText("");
                    salaireField.setText("");
                    primeField.setText("");
                    pseudoField.setText("");
                } else {
                    JOptionPane.showMessageDialog(panel, 
                        "Erreur lors de l'ajout du programmeur", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, 
                    "Veuillez entrer des valeurs valides pour l'année (compris entre "+ Programmeur.anneeNaissanceMin + " et "+ Programmeur.anneeNaissanceMax +"), le salaire et la prime", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton clearButton = new JButton("Effacer");
        clearButton.addActionListener(e -> {
            nomField.setText("");
            prenomField.setText("");
            adresseField.setText("");
            anneeField.setText("");
            hobbyField.setText("");
            responsableField.setText("");
            salaireField.setText("");
            primeField.setText("");
            pseudoField.setText("");
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Crée le panneau de suppression d'un programmeur
     */
    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Supprimer un programmeur", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panneau central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champ ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("ID du programmeur à supprimer :"), gbc);

        gbc.gridx = 1;
        JTextField idField = new JTextField(15);
        centerPanel.add(idField, gbc);

        // Zone d'information
        JTextArea infoArea = new JTextArea(8, 40);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(infoArea);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(scrollPane, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton previewButton = new JButton("Voir le programmeur");
        previewButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Optional<Programmeur> optProg = actionBDD.getProgrammeur(id);
                
                if (optProg.isPresent()) {
                    Programmeur p = optProg.get();
                    infoArea.setText(
                        "Programmeur trouvé :\n\n" +
                        "ID             : " + p.getId() + "\n" +
                        "Nom            : " + p.getNom() + "\n" +
                        "Prénom         : " + p.getPrenom() + "\n" +
                        "Année naissance: " + p.getAnneeNaissance() + "\n" +
                        "Pseudo         : " + p.getPseudo()
                    );
                } else {
                    infoArea.setText("Aucun programmeur trouvé avec l'ID " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, 
                    "Veuillez entrer un ID valide", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.setForeground(Color.RED);
        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                
                int confirm = JOptionPane.showConfirmDialog(
                    panel,
                    "Êtes-vous sûr de vouloir supprimer ce programmeur ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = actionBDD.deleteProgrammeur(id);
                    if (success) {
                        JOptionPane.showMessageDialog(panel, 
                            "Programmeur supprimé avec succès!", 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                        idField.setText("");
                        infoArea.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, 
                            "Erreur: Programmeur non trouvé ou impossible à supprimer", 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, 
                    "Veuillez entrer un ID valide", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(previewButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    
    /**
     * Crée le panneau de modification du programmeur
     */
    private JPanel createUpdateProgPanel() {
      JPanel panel = new JPanel(new BorderLayout());
      panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      
      // Titre
      JLabel titleLabel = new JLabel("Modifier les informations d'un programmeur",
                                     SwingConstants.CENTER);
      titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
      titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
      panel.add(titleLabel, BorderLayout.NORTH);
      
      // Panneau central
      JPanel centerPanel = new JPanel(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 10, 10, 10);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      
      // Champ ID
      gbc.gridx = 0;
      gbc.gridy = 0;
      centerPanel.add(new JLabel("ID du programmeur :"), gbc);
      
      gbc.gridx = 1;
      JTextField idField = new JTextField(15);
      centerPanel.add(idField, gbc);

      // Formulaire
      JPanel formPanel = new JPanel(new GridBagLayout());
      gbc.insets = new Insets(5, 5, 5, 5);
      gbc.fill = GridBagConstraints.HORIZONTAL;

      // Champs du formulaire
      JTextField nomField = new JTextField(20);
      JTextField prenomField = new JTextField(20);
      JTextField adresseField = new JTextField(20);
      JTextField anneeField = new JTextField(20);
      JTextField hobbyField = new JTextField(20);
      JTextField responsableField = new JTextField(20);
      JTextField salaireField = new JTextField(20);
      JTextField primeField = new JTextField(20);
      JTextField pseudoField = new JTextField(20);

      // Ajouter les champs
      int row = 0;
      addFormField(formPanel, gbc, row++, "Nom :", nomField);
      addFormField(formPanel, gbc, row++, "Prénom :", prenomField);
      addFormField(formPanel, gbc, row++, "Adresse :", adresseField);
      addFormField(formPanel, gbc, row++, "Année de naissance :", anneeField);
      addFormField(formPanel, gbc, row++, "Hobby :", hobbyField);
      addFormField(formPanel, gbc, row++, "Responsable :", responsableField);
      addFormField(formPanel, gbc, row++, "Salaire :", salaireField);
      addFormField(formPanel, gbc, row++, "Prime :", primeField);
      addFormField(formPanel, gbc, row++, "Pseudo :", pseudoField);

      formPanel.setVisible(false);
      centerPanel.add(formPanel, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      gbc.weighty = 1.0;
      gbc.fill = GridBagConstraints.BOTH;
      
      panel.add(centerPanel, BorderLayout.CENTER);
      
      // Boutons
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      
      JButton searchButton = new JButton("Rechercher");
      searchButton.addActionListener(e -> {
        try {
          int id = Integer.parseInt(idField.getText().trim());
          Optional<Programmeur> optProg = actionBDD.getProgrammeur(id);
          
          if (optProg.isPresent()) {
            Programmeur p = optProg.get();
            
            // Mettre à jour les champs du formulaire avec les données du programmeurs
            formPanel.setVisible(true);
            
            nomField.setText(p.getNom());
            prenomField.setText(p.getPrenom());
            adresseField.setText(p.getAdresse());
            anneeField.setText(String.valueOf(p.getAnneeNaissance()));
            hobbyField.setText(p.getHobby());
            responsableField.setText(p.getResponsable());
            salaireField.setText(String.valueOf(p.getSalaire()));
            primeField.setText(String.valueOf(p.getPrime()));
            pseudoField.setText(p.getPseudo());
          } else {
            JOptionPane.showMessageDialog(panel, 
                "Aucun programmeur trouvé avec l'ID " + id, 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(panel, 
              "Veuillez entrer un ID valide", 
              "Erreur", 
              JOptionPane.ERROR_MESSAGE);
        }
      });
      
      JButton updateButton = new JButton("Mettre à jour");
      updateButton.addActionListener(e -> {
        try {
          int id = Integer.parseInt(idField.getText().trim());
          String nouveauNom = nomField.getText().trim();
          String nouveauPrenom = prenomField.getText().trim();
          String nouvelleAdresse = adresseField.getText().trim();
          int nouvelleAnnee = Integer.parseInt(anneeField.getText().trim());

          if (nouvelleAnnee < Programmeur.anneeNaissanceMin || nouvelleAnnee > Programmeur.anneeNaissanceMax) {
              throw new NumberFormatException();
          }
          String nouveauHobby = hobbyField.getText().trim();
          String nouveauResponsable = responsableField.getText().trim();
          int nouveauSalaire = Integer.parseInt(salaireField.getText().trim());
          int nouvellePrime = Integer.parseInt(primeField.getText().trim());
          String nouveauPseudo = pseudoField.getText().trim();
          
          Optional<Programmeur> optProg = actionBDD.getProgrammeur(id);
          if (optProg.isPresent()) {
            Programmeur p = optProg.get();
            p.setNom(nouveauNom);
            p.setPrenom(nouveauPrenom);
            p.setAdresse(nouvelleAdresse);
            p.setSalaire(nouvelleAnnee);
            p.setHobby(nouveauHobby);
            p.setResponsable(nouveauResponsable);
            p.setSalaire(nouveauSalaire);
            p.setSalaire(nouvellePrime);
            p.setPseudo(nouveauPseudo);
            
            boolean success = actionBDD.updateProgrammeur(id, p);
            if (success) {
              JOptionPane.showMessageDialog(panel, 
                  "Programmeur mis à jour avec succès!", 
                  "Succès", 
                  JOptionPane.INFORMATION_MESSAGE);
            } else {
              JOptionPane.showMessageDialog(panel, 
                  "Erreur lors de la mise à jour du programmeur", 
                  "Erreur", 
                  JOptionPane.ERROR_MESSAGE);
            }
          } else {
            JOptionPane.showMessageDialog(panel, 
                "Programmeur non trouvé avec l'ID " + id, 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(panel, 
              "Veuillez entrer des valeurs valides", 
              "Erreur", 
              JOptionPane.ERROR_MESSAGE);
        }
      });
      
      buttonPanel.add(searchButton);
      buttonPanel.add(updateButton);
      panel.add(buttonPanel, BorderLayout.SOUTH);
      
      return panel;
    }
      
    /**
     * Crée le panneau de modification du salaire
     */
    private JPanel createUpdateSalaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Modifier le salaire d'un programmeur", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panneau central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champ ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("ID du programmeur :"), gbc);

        gbc.gridx = 1;
        JTextField idField = new JTextField(15);
        centerPanel.add(idField, gbc);

        // Champ nouveau salaire
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Nouveau salaire :"), gbc);

        gbc.gridx = 1;
        JTextField salaireField = new JTextField(15);
        centerPanel.add(salaireField, gbc);

        // Zone d'information
        JTextArea infoArea = new JTextArea(8, 40);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(infoArea);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(scrollPane, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Optional<Programmeur> optProg = actionBDD.getProgrammeur(id);
                
                if (optProg.isPresent()) {
                    Programmeur p = optProg.get();
                    infoArea.setText(
                        "Programmeur trouvé :\n\n" +
                        "Nom            : " + p.getNom() + " " + p.getPrenom() + "\n" +
                        "Salaire actuel : " + p.getSalaire() + " €\n" +
                        "Prime          : " + p.getPrime() + " €\n\n" +
                        "Entrez le nouveau salaire et cliquez sur 'Mettre à jour'"
                    );
                } else {
                    infoArea.setText("Aucun programmeur trouvé avec l'ID " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, 
                    "Veuillez entrer un ID valide", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton updateButton = new JButton("Mettre à jour");
        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int nouveauSalaire = Integer.parseInt(salaireField.getText().trim());
                
                Optional<Programmeur> optProg = actionBDD.getProgrammeur(id);
                if (optProg.isPresent()) {
                    Programmeur p = optProg.get();
                    p.setSalaire(nouveauSalaire);
                    
                    boolean success = actionBDD.updateProgrammeur(id, p);
                    if (success) {
                        JOptionPane.showMessageDialog(panel, 
                            "Salaire mis à jour avec succès!", 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                        infoArea.setText("Salaire mis à jour : " + nouveauSalaire + " €");
                        salaireField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, 
                            "Erreur lors de la mise à jour du salaire", 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, 
                        "Programmeur non trouvé avec l'ID " + id, 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, 
                    "Veuillez entrer des valeurs valides", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Ajoute un champ de formulaire
     */

    
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }
}
