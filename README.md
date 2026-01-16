# Gestion de Projets et Programmeurs

Application Java pour la gestion des programmeurs et des projets avec interface console et interface graphique Swing.

## Description

Ce projet permet de gérer une base de données de programmeurs et de projets. Il offre les fonctionnalités suivantes :
- Gestion des programmeurs (CRUD : Créer, Lire, Modifier, Supprimer)
- Gestion des projets
- Association programmeurs-projets
- Interface console textuelle
- Interface graphique Swing

## Prérequis

- Java 8 ou supérieur
- MySQL Server 5.7 ou supérieur
- MySQL Connector/J (inclus dans le dossier `lib/`)

## Installation

### 1. Configuration de la base de données

1. Démarrer MySQL Server
2. Créer la base de données et les tables en important le fichier SQL :

```bash
mysql -u root -p < bdtpjava.sql
```

Ou depuis phpMyAdmin :
- Importer le fichier `bdtpjava.sql`

**Note :** La base de données utilise les paramètres de connexion suivants :
- URL : `jdbc:mysql://localhost:3306/bdtpjava`
- Utilisateur : `root`
- Mot de passe : `root`

### 2. Compilation du projet

Le projet peut être compilé manuellement ou avec un IDE.

#### Compilation manuelle :

```bash
# Créer le répertoire bin s'il n'existe pas
mkdir -p bin

# Compiler les sources
javac -cp "lib/*:." -d bin src/*/*.java src/*/*/*.java
```

## Lancement de l'application

### Version Console

```bash
# Depuis la racine du projet
java -cp "bin:lib/*" main.Start
```

### Version Interface Graphique

```bash
# Depuis la racine du projet
java -cp "bin:lib/*" gui.MainFrame
```
## Fonctionnalités

### Menu Console
1. Afficher tous les programmeurs
2. Afficher un programmeur
3. Supprimer un programmeur
4. Ajouter un programmeur
5. Modifier le salaire d'un programmeur
6. Afficher tous les projets
7. Afficher les programmeurs d'un projet
8. Quitter

### Interface Graphique
- Menu principal avec navigation
- Gestion complète des programmeurs (CRUD)
- Gestion des projets

## Structure du projet

```
├── src/
│   ├── main/
│   │   └── Start.java              # Point d'entrée console
│   ├── gui/
│   │   ├── MainFrame.java          # Fenêtre principale GUI
│   │   ├── MenuPanel.java          # Panneau menu GUI
│   │   ├── ProgrammeurPanel.java   # Panneau programmeurs GUI
│   │   └── ProjetPanel.java        # Panneau projets GUI
│   ├── menu/
│   │   └── Menu.java               # Menu console
│   ├── database/
│   │   ├── ActionBDD.java          # Interface base de données
│   │   └── ActionBDDImpl.java      # Implémentation base de données
│   └── entity/
│       ├── Programmeur.java        # Classe Programmeur
│       ├── Projet.java             # Classe Projet
│       └── Etat.java               # Énumération État
├── lib/
│   ├── mysql-connector-j-8.2.0.jar # Driver MySQL
│   └── protobuf-java-3.21.9.jar    # Dépendance protobuf
├── bin/                            # Classes compilées
├── bdtpjava.sql                    # Script SQL de la base
├── README.md                       # Ce fichier
└── .gitignore
```

## Base de données

### Tables principales
- `programmeur` : Informations sur les programmeurs
- `projet` : Informations sur les projets
- `programmeur_projet` : Table de liaison many-to-many

### Connexion
L'application se connecte à MySQL avec les paramètres codés en dur :
- Host : localhost:3306
- Base : bdtpjava
- User : root
- Password : root

## Technologies utilisées

- Java 8+
- MySQL 5.7+
- JDBC
- Swing (pour l'interface graphique)

## Développement

### Avec un IDE
1. Ouvrir le projet dans votre IDE (Eclipse, IntelliJ IDEA, VS Code)
2. Ajouter le JAR MySQL Connector au classpath
3. Compiler et exécuter `main.Start` ou `gui.MainFrame`

### Avec VS Code
- Installer l'extension Java
- Ouvrir le dossier du projet
- Utiliser les tâches de build intégrées

## Dépannage

### Problèmes de connexion MySQL
- Vérifier que MySQL Server est démarré
- Vérifier les identifiants de connexion dans `ActionBDDImpl.java`

### Erreurs de compilation
- Vérifier que le classpath inclut `lib/*`
- S'assurer que Java 8+ est installé

### Port MySQL occupé
- Vérifier qu'aucun autre service n'utilise le port 3306
- Modifier le port dans la configuration si nécessaire