# Feedback

## Introduction

Bonne introduction.

## Échéancier

Bon échéancier.

## Glossaire 

Il manque des termes importants : "Application MaVille", "Code de la ville", "Notifications personnalisées", "Info Entraves et Travaux", "Planification participative" et "Signaler un problème".

## Cas d'utilisation

Il manque la "Ville" comme acteur. 

Il manque "Modifier son profil" et "Consulter ses notifications" pour l'utilisateur.

Il manque "Envoyer une notification" pour l'intervenant.

Il n'y a pas de relation include avec "Se connecter au système".

La relation entre "intervenant" et "Donner candidature pour travaux" est inutile.

## Scénarios

Il y a un manque d'alternance entre acteur et système (max 2 étapes successives de la même source) pour certains scénarios.

Les étapes de connextion auraientt pu être une précondition ou un appel au CU "Se connecter au système" pour gagner du temps.

Il manque les scénarios: "Se connecter au système", "S'abonner à un projet", "Donner avis sur des travaux", "Filtrer la liste des travaux" et "Consulter les préférences des résidents".

### CU: Consulter la liste des requêtes de travail

5b -> 5a, car c'est le 1er(a) scénario alternatif de l'étape 5.

5c -> 5b

Il n'y a pas de scénario alternatifs pour l'extension.

### CU: Soumettre sa candidature pour une requête

6c.1- "Le scénario reprend à l’étape 5" est plus juste.

Il n'y a pas de scénario alternatifs pour l'extension.

### CU: Soumettre un nouveau projet de travaux

5c- Il manque l'étape d'appel de la consultation

### CU: Signaler un problème à la ville

Les scénarios alternatifs ne sont pas dans le bon ordre.

Il manque une étape entre 7a.1 et 7a.2.

### CU: Soumettre une requête de travail

7- Que se passe-t-il si l'information n'est pas valide?

2a- Les numéros des étapes ne correspondent pas

## Diagramme d'activités

Il manque le diagramme d'activité pour "S'abonner à un projet".

On peut boucler à l'infini dans plusieurs diagrammes.

## Analyse

### Risques

Bons choix de risques.

### Besoins non fonctionnels

Manque de description, caractérisation par un critère de qualité et justification par le contexte.

### Besoins matériels

Bonne solution.

### Solution de stockage

Doit-on sécuriser les données?

### Solution d'intégration

Bonne solution.

## Prototype

Fichier JAR fonctionnel.

## Git 

README et Release présents.

3/3 membres ont fait au moins 1 commit.

## Rapport 

3 images ne s'affichent pas, en raison des accents.

Pour le diagramme d'activité "Fonctionnalités reliées aux requêtes de travaux", l'image source n'est pas la bonne.