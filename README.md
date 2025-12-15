# Kids Learning - Application Android

## Description
Kids Learning est une application Android éducative destinée aux enfants pour apprendre et tracer les lettres de l'alphabet français et arabe.

## Fonctionnalités

### 🏠 Écran Principal
- Logo de l'application en haut
- Deux cartes colorées : une pour le français et une pour l'arabe
- Interface adaptée aux enfants avec de grandes icônes

### 📝 Liste des Lettres
- Grille de lettres organisée par langue
- Système de couleurs pour indiquer le statut :
  - **Vert** : Lettre validée/complétée
  - **Rouge** : Lettre non validée mais accessible
  - **Gris** : Lettre verrouillée

### ✏️ Zone de Dessin
- Canvas interactif pour tracer les lettres
- Lettre en arrière-plan semi-transparente comme guide
- Boutons de contrôle :
  - 🔊 **Son** : Écouter la prononciation de la lettre
  - 🗑️ **Effacer** : Supprimer tout le tracé
  - ↶ **Annuler** : Annuler le dernier trait
  - ✓ **Valider** : Vérifier le tracé

### 🎯 Système de Progression
- Sauvegarde locale de la progression
- Validation des lettres tracées
- Messages de félicitations en français ou en arabe

## Architecture

### Structure du Projet
```
app/src/main/
├── java/com/example/kids_learning/
│   ├── MainActivity.kt              # Écran principal
│   ├── LetterListActivity.kt        # Liste des lettres
│   ├── DrawingActivity.kt           # Zone de dessin
│   ├── adapters/
│   │   └── LetterAdapter.kt         # Adaptateur RecyclerView
│   ├── data/
│   │   ├── Letter.kt                # Modèle de données
│   │   └── ProgressManager.kt       # Gestion de la progression
│   └── views/
│       └── DrawingView.kt           # Vue personnalisée pour le dessin
├── res/
│   ├── layout/                      # Layouts XML
│   ├── drawable/                    # Ressources graphiques
│   ├── values/                      # Couleurs, styles, strings
│   └── font/                        # Polices personnalisées
└── assets/
    └── letters_data.json            # Données des lettres
```

### Technologies Utilisées
- **Kotlin** : Langage principal
- **RecyclerView** : Affichage des listes de lettres
- **CardView** : Interface utilisateur moderne
- **Canvas** : Dessin personnalisé
- **SharedPreferences** : Sauvegarde locale
- **JSON** : Stockage des données hors-ligne

## Installation

1. Cloner le projet
2. Ouvrir dans Android Studio
3. Synchroniser les dépendances Gradle
4. Compiler et installer sur un appareil Android

## Configuration Requise
- **Android API 24+** (Android 7.0)
- **Kotlin 1.8+**
- **Android Studio Arctic Fox+**

## Fonctionnalités Futures
- [ ] Ajout de fichiers audio réels pour la prononciation
- [ ] Algorithme de reconnaissance de tracé plus avancé
- [ ] Animations et effets visuels
- [ ] Mode multijoueur
- [ ] Statistiques détaillées de progression

## Couleurs et Design
L'application utilise une palette de couleurs adaptée aux enfants :
- **Orange** (#FF5722) : Français
- **Vert** (#4CAF50) : Arabe et validation
- **Bleu** (#2196F3) : Tracé
- **Fond crème** (#FFF3E0) : Arrière-plan doux

## Contribution
Ce projet est conçu comme une base éducative. Les contributions pour améliorer l'expérience utilisateur et ajouter de nouvelles fonctionnalités sont les bienvenues.