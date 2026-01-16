package gui;

import javax.swing.*;
import java.awt.*;

import database.ActionBDD;
import database.ActionBDDImpl;

/**
 * Fenêtre principale de l'application
 * Contient le menu principal et gère la navigation entre les différentes vues
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private ActionBDD actionBDD;
    private JPanel contentPanel;
    private CardLayout cardLayout;

    // Panneaux pour les différentes vues
    private ProgrammeurPanel programmeurPanel;
    private ProjetPanel projetPanel;
    private MenuPanel menuPanel;

    /**
     * Constructeur de la fenêtre principale
     */
    public MainFrame() {
        this.actionBDD = new ActionBDDImpl();
        initializeFrame();
        createMenuBar();
        createContentPanel();
    }

    /**
     * Initialise la fenêtre principale
     */
    private void initializeFrame() {
        setTitle("Gestion de Projets et Programmeurs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    /**
     * Crée la barre de menu
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Programmeurs
        JMenu menuProgrammeur = new JMenu("Programmeurs");
        
        JMenuItem afficherTous = new JMenuItem("Afficher tous les programmeurs");
        afficherTous.setAccelerator(KeyStroke.getKeyStroke("control 1"));
        afficherTous.addActionListener(e -> showProgrammeurPanel(ProgrammeurPanel.VIEW_ALL));
        
        JMenuItem afficherUn = new JMenuItem("Afficher un programmeur");
        afficherUn.setAccelerator(KeyStroke.getKeyStroke("control 2"));
        afficherUn.addActionListener(e -> showProgrammeurPanel(ProgrammeurPanel.VIEW_ONE));
        
        JMenuItem supprimer = new JMenuItem("Supprimer un programmeur");
        supprimer.setAccelerator(KeyStroke.getKeyStroke("control 3"));
        supprimer.addActionListener(e -> showProgrammeurPanel(ProgrammeurPanel.DELETE));
        
        JMenuItem ajouter = new JMenuItem("Ajouter un programmeur");
        ajouter.setAccelerator(KeyStroke.getKeyStroke("control 4"));
        ajouter.addActionListener(e -> showProgrammeurPanel(ProgrammeurPanel.ADD));
        

        JMenuItem modifierProg = new JMenuItem("Modifier un programmeur");
        modifierProg.setAccelerator(KeyStroke.getKeyStroke("control 6"));
        modifierProg.addActionListener(e -> showProgrammeurPanel(ProgrammeurPanel.UPDATE_PROG));
        
        menuProgrammeur.add(afficherTous);
        menuProgrammeur.add(afficherUn);
        menuProgrammeur.addSeparator();
        menuProgrammeur.add(ajouter);
        menuProgrammeur.add(modifierProg);
        menuProgrammeur.addSeparator();
        menuProgrammeur.add(supprimer);

        // Menu Projets
        JMenu menuProjet = new JMenu("Projets");

        JMenuItem afficherProjets = new JMenuItem("Afficher tous les projets");
        afficherProjets.setAccelerator(KeyStroke.getKeyStroke("control 6"));
        afficherProjets.addActionListener(e -> showProjetPanel(ProjetPanel.VIEW_ALL));

        JMenuItem programmeursProjet = new JMenuItem("Programmeurs d'un projet");
        programmeursProjet.setAccelerator(KeyStroke.getKeyStroke("control 7"));
        programmeursProjet.addActionListener(e -> showProjetPanel(ProjetPanel.VIEW_PROGRAMMERS));

        JMenuItem ajouterProjet = new JMenuItem("Ajouter un projet");
        ajouterProjet.setAccelerator(KeyStroke.getKeyStroke("control 8"));
        ajouterProjet.addActionListener(e -> showProjetPanel(ProjetPanel.ADD));

        JMenuItem updateProjet = new JMenuItem("Mettre à jour un projet");
        updateProjet.addActionListener(e -> showProjetPanel(ProjetPanel.UPDATE));

        menuProjet.add(afficherProjets);
        menuProjet.add(programmeursProjet);
        menuProjet.addSeparator();
        menuProjet.add(ajouterProjet);
        menuProjet.add(updateProjet);

        // Menu Application
        JMenu menuApplication = new JMenu("Application");
        
        JMenuItem accueil = new JMenuItem("Accueil");
        accueil.setAccelerator(KeyStroke.getKeyStroke("control H"));
        accueil.addActionListener(e -> showMenuPanel());
        
        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        quitter.addActionListener(e -> {
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
        
        menuApplication.add(accueil);
        menuApplication.addSeparator();
        menuApplication.add(quitter);

        // Menu Aide
        JMenu menuAide = new JMenu("Aide");
        JMenuItem aPropos = new JMenuItem("À propos");
        aPropos.addActionListener(e -> showAboutDialog());
        menuAide.add(aPropos);

        // Ajouter les menus à la barre
        menuBar.add(menuApplication);
        menuBar.add(menuProgrammeur);
        menuBar.add(menuProjet);
        menuBar.add(menuAide);

        setJMenuBar(menuBar);
    }

    /**
     * Crée le panneau de contenu avec CardLayout
     */
    private void createContentPanel() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Créer les différents panneaux
        menuPanel = new MenuPanel(this);
        programmeurPanel = new ProgrammeurPanel(actionBDD);
        projetPanel = new ProjetPanel(actionBDD);

        // Ajouter les panneaux au CardLayout
        contentPanel.add(menuPanel, "MENU");
        contentPanel.add(programmeurPanel, "PROGRAMMEUR");
        contentPanel.add(projetPanel, "PROJET");

        add(contentPanel, BorderLayout.CENTER);

        // Afficher le menu par défaut
        showMenuPanel();
    }

    /**
     * Affiche le panneau de menu principal
     */
    public void showMenuPanel() {
        cardLayout.show(contentPanel, "MENU");
    }

    /**
     * Affiche le panneau des programmeurs avec l'action spécifiée
     * @param action Action à effectuer (VIEW_ALL, VIEW_ONE, ADD, DELETE, UPDATE_SALARY)
     */
    public void showProgrammeurPanel(String action) {
        programmeurPanel.setAction(action);
        cardLayout.show(contentPanel, "PROGRAMMEUR");
    }

    /**
     * Affiche le panneau des projets avec l'action spécifiée
     * @param action Action à effectuer (VIEW_ALL, VIEW_PROGRAMMERS)
     */
    public void showProjetPanel(String action) {
        projetPanel.setAction(action);
        cardLayout.show(contentPanel, "PROJET");
    }

    /**
     * Affiche la boîte de dialogue "À propos"
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(
            this,
            "Gestion de Projets et Programmeurs\n" +
            "Version 1.0\n\n" +
            "Application de gestion développée en Java Swing\n" +
            "Projet LSI1 2025-2026",
            "À propos",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Point d'entrée pour lancer l'application GUI
     */
    public static void main(String[] args) {
        // Utiliser le Look and Feel du système
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lancer l'interface graphique dans le thread EDT
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
