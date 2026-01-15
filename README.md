# 💊 Projet : Gestion de Pharmacie (Java Swing)

## 📌 Description générale

Cette application Desktop permet la gestion complète d’une pharmacie.
Elle offre des outils pour gérer les médicaments, les fournisseurs et les ventes, avec un suivi automatique du stock, des alertes intelligentes, et des statistiques graphiques.

L’application est développée en Java Swing, connectée à une base de données MySQL, et suit une architecture claire basée sur le pattern DAO.

## 🎯 Objectifs du projet

Organiser les données de la pharmacie

Assurer un suivi fiable du stock

Automatiser l’enregistrement des ventes

Visualiser l’activité via des graphiques

## ⚙️ Fonctionnalités
### 🧪 Médicaments

Ajouter un médicament

Modifier les informations

Supprimer un médicament

Afficher la liste des médicaments

Filtrer par famille

### 🚚 Fournisseurs

Ajouter / modifier / supprimer un fournisseur

Consulter les fournisseurs disponibles

### 🧾 Ventes

Enregistrer une vente

Décrémenter automatiquement le stock

Historique des ventes

### 🚨 Gestion du stock

Détection automatique du stock faible

Alerte visuelle (message / couleur)

### 📊 Statistiques

Graphique des ventes par famille

Réalisé avec JFreeChart

Données mises à jour dynamiquement

## 🧱 Architecture du projet
```text
src/
│
├── dao/            # Accès aux données (DAO)
├── entities/       # Entités (Patient, Medcin, RDV ...)
├── gui/            # Interfaces graphiques (JFrame, JInternalFrame, JDialog)
├── connexion/      # Connexion à la base de données
```

## 🛠️ Technologies utilisées

Java SE (Swing)

JDBC

MySQL

JFreeChart & JCommon

NetBeans IDE

Architecture DAO / MVC

## 🖥️ Interface utilisateur

Application Desktop

JFrame principal avec JDesktopPane

Navigation simple via JInternalFrame

Interface claire, légère et moderne

## 📐 MCD — Modèle Conceptuel de Données (Merise)
TODO

## ▶️ Création des tables (MySQL)
```text
-- Création de la table Medicament
CREATE TABLE Medicament(
    idMed INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(70),
    famille VARCHAR(70),
    prix DOUBLE,
    stock INT
);

-- Création de la table Fournisseur
CREATE TABLE Fournisseur(
    idFour INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(70),
    ville VARCHAR(70),
    contact VARCHAR(50)
);

-- Création de la table Vente
CREATE TABLE Vente(
    dateVente DATE,
    quantite INT,
    idMed INT,
    PRIMARY KEY (idMed, dateVente),
    FOREIGN KEY (idMed) REFERENCES Medicament(idMed)
);
```
## ✅ Vidéo
TODO

Développé par Idhssaine Hamza
Gestion de pharmacie
