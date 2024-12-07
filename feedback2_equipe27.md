# Feedback

## Révision

### Échéancier

Échéancier mis à jour

### diagrammes CU 

Il manque un nouvel acteur "API de la ville".

"Donner avis sur des travaux" n'est plus pertinent.

Il manque un nouveau CU "Consulter ou Chercher les entraves routières".

Il manque des corrections

### Diagrammes d'activités 

Les diagrammes d'activités sont absents

### Analyse

Analyse mise à jour

## Architecture

Il manque la frontière du système dans les diagrammes de conteneur et de composant.

Les requêtes vers l'API devrait être plus explicites (HTTP?)

Il manque les composantes vue et modèle pour faire une architecture MVC.

## Diagramme de classe

Il manque des méthodes dans la classe IntervenantController comme run() et validerInformations().

Il manque les multiplicités pour certaines relations, par exemple : les compositions.

## Diagramme de séquence 

Il y a des méthodes qui n'existe pas dans le diagramme de classe. Ex: 5.1 validerDate()

### Consulter les entraves

Comment on récupère les entraves?

### Soumettre une requête de travail 

2.1.1 la flèche est mauvaise.

### Consulter la liste des requêtes de travail

Comment on récupère les requêtes?

## Discussion design

Bien.

## Implémentation

Il manque 2 résidents et intervenants pré-initialisées.

L'application plante lors de la saisie d'un String comme date dans "Soumettre une requête de travail".

Il manque "Consulter la liste des requêtes de travail"

## Tests unitiaire

Tests exécutables et passent tous.

## Rapport et Git

L'image pour "Consulter la liste de requêtes de travail" ne s'affiche pas.