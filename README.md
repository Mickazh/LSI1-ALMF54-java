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

- Java
- MySQL Server
- MySQL Connector/J (inclus dans le dossier `lib/`)

## Installation

### 1. Configuration de la base de données

1. Démarrer MySQL Server
2. Créer la base de données et les tables en important le fichier SQL :

```bash
mysql -u root -p < bdtpjava.sql
```

Ou importez la base de données depuis un explorateur de base de données comme PhpMyAdmin 

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
javac -cp "lib/*." -d bin src/**/*.java
```

## Lancement de l'application

### Version Console

```bash
# Depuis la racine du projet sur un système UNIX
java -cp "bin:lib/*" main.Start

# Depuis la racine du projet sur un système Windows
java -cp "bin:lib/*" main.Start
```

Le projet peut également être lancé avec le jar
```bash
java -jar GestionProjets-Terminal.jar
```

### Version Interface Graphique

```bash
# Depuis la racine du projet sur un système UNIX
java -cp "bin:lib/*" gui.MainFrame
# Depuis la racine du projet sur un système Windows
java -cp "bin;lib/*" gui.MainFrame
```

Le projet peut également être lancé avec le jar
```bash
java -jar GestionProjets-GUI.jar
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
│   ├── mysql-connector-java-9.5.0.jar # Driver MySQL
├── bin/                            # Classes compilées
├── bdtpjava.sql                    # Script SQL de la base
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

- Java swing car elle fait partie de la librairie standard
- MySQL car nous avions l'habitude

## Dépannage

### Problèmes de connexion MySQL
- Vérifier que MySQL Server est démarré
- Vérifier les identifiants de connexion dans `ActionBDDImpl.java`

### Erreurs de compilation
- Vérifier que le classpath inclut `lib/*`

## Générer la javadoc à partir de la documentation

```bash
cd src
javadoc -d ../docs -sourcepath ./ -subpackages *
```
Puis, il suffit d'ouvrir `docs/index.html` dans un navigateur