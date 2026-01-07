package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

import database.ActionBDDImpl;
import entity.Programmeur;
import entity.Projet;

/**
 * Panneau de gestion des projets
 * Permet d'afficher les projets et les programmeurs associés
 */
public class ProjetPanel extends JPanel {
    // Constantes pour les actions
    public static final String VIEW_ALL = "VIEW_ALL";
    public static final String VIEW_PROGRAMMERS = "VIEW_PROGRAMMERS";

    private ActionBDDImpl actionBDD;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    // Panneaux pour différentes actions
    private JPanel viewAllPanel;
    private JPanel viewProgrammersPanel;

    /**
     * Constructeur du panneau projet
     * @param actionBDD Instance de la classe d'accès aux données
     */
    public ProjetPanel(ActionBDDImpl actionBDD) {
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
        viewProgrammersPanel = createViewProgrammersPanel();

        // Ajouter les panneaux au CardLayout
        contentPanel.add(viewAllPanel, VIEW_ALL);
        contentPanel.add(viewProgrammersPanel, VIEW_PROGRAMMERS);

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
     * Crée le panneau d'affichage de tous les projets
     */
    private JPanel createViewAllPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Titre
        JLabel titleLabel = new JLabel("Liste de tous les projets", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"ID", "Intitulé", "Date Début", "Date Fin", "État", "Nb Programmeurs"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        
        // Ajuster la largeur des colonnes
        table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200);  // Intitulé
        table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Date Début
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Date Fin
        table.getColumnModel().getColumn(4).setPreferredWidth(100);  // État
        table.getColumnModel().getColumn(5).setPreferredWidth(120);  // Nb Programmeurs
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panneau d'information détaillée
        JTextArea detailArea = new JTextArea(6, 40);
        detailArea.setEditable(false);
        detailArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailArea.setBorder(BorderFactory.createTitledBorder("Détails du projet sélectionné"));
        JScrollPane detailScrollPane = new JScrollPane(detailArea);
        
        // Ajouter un écouteur de sélection
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int projetId = (int) table.getValueAt(selectedRow, 0);
                    List<Projet> projets = actionBDD.getProjets();
                    
                    for (Projet projet : projets) {
                        if (projet.getId() == projetId) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            StringBuilder details = new StringBuilder();
                            details.append("Intitulé     : ").append(projet.getIntitule()).append("\n");
                            details.append("Date début   : ").append(sdf.format(projet.getDateDebut())).append("\n");
                            details.append("Date fin     : ").append(sdf.format(projet.getDateFin())).append("\n");
                            details.append("État         : ").append(projet.getEtat()).append("\n");
                            details.append("Programmeurs : ").append(projet.getProgrammeurs().size()).append("\n\n");
                            
                            if (!projet.getProgrammeurs().isEmpty()) {
                                details.append("Liste des programmeurs :\n");
                                for (Programmeur p : projet.getProgrammeurs()) {
                                    details.append("  - ").append(p.getNom()).append(" ")
                                           .append(p.getPrenom()).append(" (")
                                           .append(p.getPseudo()).append(")\n");
                                }
                            } else {
                                details.append("Aucun programmeur affecté à ce projet.");
                            }
                            
                            detailArea.setText(details.toString());
                            break;
                        }
                    }
                }
            }
        });

        // Panel du bas avec les boutons et les détails
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(detailScrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton refreshButton = new JButton("Rafraîchir");
        refreshButton.addActionListener(e -> {
            loadProjetsInTable(tableModel);
            detailArea.setText("");
        });
        buttonPanel.add(refreshButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Charger les données initiales
        loadProjetsInTable(tableModel);

        return panel;
    }

    /**
     * Charge les projets dans le tableau
     */
    private void loadProjetsInTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        List<Projet> projets = actionBDD.getProjets();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Projet p : projets) {
            Object[] row = {
                p.getId(),
                p.getIntitule(),
                sdf.format(p.getDateDebut()),
                sdf.format(p.getDateFin()),
                p.getEtat(),
                p.getProgrammeurs().size()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Rafraîchit le panneau d'affichage de tous les projets
     */
    private void refreshViewAllPanel() {
        Component[] components = viewAllPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                JTable table = (JTable) scrollPane.getViewport().getView();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                loadProjetsInTable(model);
                break;
            }
        }
    }

    /**
     * Crée le panneau d'affichage des programmeurs d'un projet
     */
    private JPanel createViewProgrammersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Programmeurs d'un projet", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panneau de sélection du projet
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Liste déroulante des projets
        JLabel selectLabel = new JLabel("Sélectionner un projet : ");
        JComboBox<String> projectComboBox = new JComboBox<>();
        projectComboBox.setPreferredSize(new Dimension(300, 30));

        // Charger les projets dans la liste déroulante
        loadProjectsInComboBox(projectComboBox);

        topPanel.add(selectLabel);
        topPanel.add(projectComboBox);

        panel.add(topPanel, BorderLayout.NORTH);

        // Table des programmeurs
        String[] columnNames = {"ID", "Nom", "Prénom", "Année Naissance", "Hobby", "Salaire", "Pseudo"};
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

        // Label de statut
        JLabel statusLabel = new JLabel("Sélectionnez un projet pour voir ses programmeurs", SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Bouton de recherche
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton searchButton = new JButton("Afficher les programmeurs");
        searchButton.addActionListener(e -> {
            String selectedProject = (String) projectComboBox.getSelectedItem();
            System.out.println(selectedProject);
            if (selectedProject != null && !selectedProject.isEmpty()) {
                // Extraire l'ID du projet depuis la chaîne
                int projetId = extractProjectId(selectedProject);
                if (projetId > 0) {
                    List<Programmeur> programmeurs = actionBDD.getProgrammeursFromProjet(projetId);
                    loadProgrammeursInTable(tableModel, programmeurs);
                    
                    if (programmeurs.isEmpty()) {
                        statusLabel.setText("Aucun programmeur trouvé pour ce projet");
                        statusLabel.setForeground(Color.ORANGE);
                    } else {
                        statusLabel.setText(programmeurs.size() + " programmeur(s) trouvé(s)");
                        statusLabel.setForeground(new Color(0, 128, 0));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(panel,
                    "Veuillez sélectionner un projet",
                    "Erreur",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton refreshProjectsButton = new JButton("Rafraîchir la liste des projets");
        refreshProjectsButton.addActionListener(e -> {
            loadProjectsInComboBox(projectComboBox);
            tableModel.setRowCount(0);
            statusLabel.setText("Liste des projets rafraîchie");
            statusLabel.setForeground(Color.BLACK);
        });

        buttonPanel.add(searchButton);
        buttonPanel.add(refreshProjectsButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Charge les projets dans la liste déroulante
     */
    private void loadProjectsInComboBox(JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        List<Projet> projets = actionBDD.getProjets();
        
        if (projets.isEmpty()) {
            comboBox.addItem("Aucun projet disponible");
        } else {
            for (Projet p : projets) {
                comboBox.addItem("[ID:" + p.getId() + "] " + p.getIntitule());
            }
        }
    }

    /**
     * Extrait l'ID du projet depuis la chaîne de sélection
     */
    private int extractProjectId(String projectString) {
        try {
            // Format: "[ID:X] Nom du projet"
            int startIndex = projectString.indexOf("[ID:") + 4;
            int endIndex = projectString.indexOf("]");
            if (startIndex > 3 && endIndex > startIndex) {
                return Integer.parseInt(projectString.substring(startIndex, endIndex));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Charge les programmeurs dans le tableau
     */
    private void loadProgrammeursInTable(DefaultTableModel tableModel, List<Programmeur> programmeurs) {
        tableModel.setRowCount(0);
        
        for (Programmeur p : programmeurs) {
            Object[] row = {
                p.getId(),
                p.getNom(),
                p.getPrenom(),
                p.getAnneeNaissance(),
                p.getHobby(),
                p.getSalaire() + " €",
                p.getPseudo()
            };
            tableModel.addRow(row);
        }
    }
}
