package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau d'accueil avec menu principal
 * Affiche les options disponibles sous forme de boutons
 */
public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private MainFrame mainFrame;

    /**
     * Constructeur du panneau de menu
     * @param mainFrame Référence vers la fenêtre principale
     */
    public MenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        initComponents();
    }

    /**
     * Initialise les composants du panneau
     */
    private void initComponents() {
        // Titre
        JLabel titleLabel = new JLabel("GESTION DE PROJETS ET PROGRAMMEURS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Panneau central avec les boutons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        // Création des boutons
        JButton btnAfficherProgrammeurs = createMenuButton(
            "1. Afficher tous les programmeurs",
            "Voir la liste complète des programmeurs"
        );
        btnAfficherProgrammeurs.addActionListener(e -> 
            mainFrame.showProgrammeurPanel(ProgrammeurPanel.VIEW_ALL));

        JButton btnAfficherProgrammeur = createMenuButton(
            "2. Afficher un programmeur",
            "Rechercher un programmeur par son ID"
        );
        btnAfficherProgrammeur.addActionListener(e -> 
            mainFrame.showProgrammeurPanel(ProgrammeurPanel.VIEW_ONE));

        JButton btnSupprimerProgrammeur = createMenuButton(
            "3. Supprimer un programmeur",
            "Supprimer un programmeur de la base de données"
        );
        btnSupprimerProgrammeur.addActionListener(e -> 
            mainFrame.showProgrammeurPanel(ProgrammeurPanel.DELETE));

        JButton btnAjouterProgrammeur = createMenuButton(
            "4. Ajouter un programmeur",
            "Ajouter un nouveau programmeur"
        );
        btnAjouterProgrammeur.addActionListener(e -> 
            mainFrame.showProgrammeurPanel(ProgrammeurPanel.ADD));

        
        JButton btnModifierProgrammeur = createMenuButton(
            "5. Modifier un programmeur",
            "Modifier les informations d'un programmeur"
        );
        btnModifierProgrammeur.addActionListener(e -> 
            mainFrame.showProgrammeurPanel(ProgrammeurPanel.UPDATE_PROG));

        JButton btnAfficherProjets = createMenuButton(
            "6. Afficher tous les projets",
            "Voir la liste complète des projets"
        );
        btnAfficherProjets.addActionListener(e -> 
            mainFrame.showProjetPanel(ProjetPanel.VIEW_ALL));

        JButton btnProgrammeursProjet = createMenuButton(
            "7. Programmeurs d'un projet",
            "Voir les programmeurs travaillant sur un projet"
        );
        btnProgrammeursProjet.addActionListener(e -> 
            mainFrame.showProjetPanel(ProjetPanel.VIEW_PROGRAMMERS));

        JButton btnQuitter = createMenuButton(
            "8. Quitter",
            "Fermer l'application"
        );
        btnQuitter.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment quitter l'application ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Ajouter les boutons au panneau
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(btnAfficherProgrammeurs, gbc);
        
        gbc.gridy = 1;
        centerPanel.add(btnAfficherProgrammeur, gbc);
        
        gbc.gridy = 2;
        centerPanel.add(btnSupprimerProgrammeur, gbc);
        
        gbc.gridy = 3;
        centerPanel.add(btnAjouterProgrammeur, gbc);
        
        gbc.gridy = 4;
        centerPanel.add(btnModifierProgrammeur, gbc);
        
        gbc.gridy = 5;
        centerPanel.add(btnAfficherProjets, gbc);
        
        gbc.gridy = 6;
        centerPanel.add(btnProgrammeursProjet, gbc);
        
        gbc.gridy = 7;
        gbc.insets = new Insets(30, 10, 10, 10);

        gbc.gridy = 9;
        centerPanel.add(btnQuitter, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Pied de page
        JLabel footerLabel = new JLabel("Sélectionnez une option", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        add(footerLabel, BorderLayout.SOUTH);
    }

    /**
     * Créer un bouton de menu avec un style uniforme
     * @param text Texte du bouton
     * @param tooltip Texte de l'info-bulle
     * @return Le bouton créé
     */
    private JButton createMenuButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(400, 50));
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Style du bouton
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 110, 160), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Effet de survol
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(90, 150, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        
        return button;
    }
}
