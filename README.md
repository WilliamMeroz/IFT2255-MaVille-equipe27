# IFT2255: Projet MaVille

Répertoire Git pour l'implémentation en Java du système MaVille dans le cadre du cours IFT2255.

Ce projet utilise Maven comme outil de gestion des dépendances. De ce fait, le répertoire Git s'efforce de suivre le [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html) recommandé par Maven.

Le code source se trouve donc au chemin de répertoires `/src/main/java/maville/equipe27/`

## Organisation du répertoire

Le projet cherche également à respecter l'architecture MVC et est donc séparé en plusieurs dossiers, distinguant les vues, les contrôleurs, les modèles et le code d'accès aux données stockées de manière permanente.

### Répertoire `controllers`
Ce répertoire contient les différents fichiers liés aux contrôleurs de l'application. Pour l'instant, nous avons des contrôleurs associés aux fonctionnalités de connexion et d'inscription des utilisateurs, ainsi qu'à celles accessibles uniquement aux résidents ou aux intervenants.

### Répertoire `enums`
Ce répertoire contient uniquement les fichiers de regroupement de constantes nécessaires dans le code.

### Répertoire `helpers`
Répertoire contenant les fichiers de code qui interagissent avec les données persistées dans les fichiers .JSON, ainsi que certains autres fichiers nécessaires à d'autres parties du projet, qui ne peuvent pas être placés dans d'autres répertoires.

### Répertoire `models`
Ce répertoire contient les fichiers de classes des différents modèles utilisés dans le code (User, Intervenant, Résident, etc.).

### Répertoire `validators`
Ce répertoire contient les fichiers de code de validation des données (par exemple, des données fournies par un utilisateur).

### Répertoire `views`
Ce répertoire contient les fichiers de code interagissant directement avec l'utilisateur par la ligne de commande.

### Fichier `Main.java`
Fichier contenant la méthode `main` du projet. 

## Exécuter le programme
Pour exécuter le programme, il faut s'assurer d'avoir les fichiers:
- `users.json`
- `requetes.json`
- `travaux_categories_mapping.json`
- `projets.json`
- `notifications.json`
- `horaires.json`
- `test_users.json`

dans le même répertoire que l'exécutable en .JAR. Des comptes préconfigurés sont mis à la disposition de l'utilisateur. Les données suivantes sont également mises à la disposition de l'utilisateur.

| Compte      | Courriel               | Mot de passe | Quartier      |
|-------------|------------------------|--------------|---------------|
| Résident    | resident1@gmail.com    | pomme123     | Plateau       |
| Résident    | resident2@gmail.com    | pomme123     | Plateau       |
| Résident    | resident3@gmail.com    | pomme123     | Outremont     |
| Résident    | resident4@gmail.com    | pomme123     | Petite Italie |
| Résident    | resident5@gmail.com    | pomme123     | Centre-Ville  |
| Intervenant | intervenant1@gmail.com | pomme123     | NA            |
| Intervenant | intervenant2@gmail.com | pomme123     | NA            |
| Intervenant | intervenant3@gmail.com | pomme123     | NA            |
| Intervenant | intervenant4@gmail.com | pomme123     | NA            |
| Intervenant | intervenant5@gmail.com | pomme123     | NA            |

Chaque résident à une requête à son nom, et l'intervenant1 à donné sa candidature à la requête du résident1. 
Pareillement, l'intervenant2 à donné sa candidature à la requête du résident 2.

Les 5 projets sont au nom de l'intervenant 1.

De plus, une version de Maven 4.0.0 et Java 21 seront nécessaires. Il n'est pas nécessaire d'installer les librairies
vous-mêmes puisque Maven le fera pour vous.

## Tests unitaires
Les tests unitaires sont séparés par ficher. Chaque fichier de test est nommé par le nom du fichier original qu'il faut tester + `Test`.
Les tests unitaires font l'utilisation des librairies [JUnit](https://junit.org/junit5/) mais également [Mockito](https://site.mockito.org/) afin de faire des mocks, stubs et spies plus facilement.

Les fichiers de tests se trouvent dans le répertoire `/src/test/java/maville/equipe27` et sont séparés dans des répertoires de la même façon que les fichiers sources.

### Exécuter les tests unitaires
Dans un IDE tel que Eclipse, Netbeans ou Intellij, il suffit d'ouvrir un des fichiers de tests et cliquer sur l'icône d'exécution des tests.
Pour exécuter les tests par ligne de commande (non-recommandé), il faut compiler les fichiers de tests en .JAR. Une documentation est disponible ici: [JUnit User Guide - Running tests](https://junit.org/junit5/docs/current/user-guide/#running-tests)

## Librairies utilisées
Le projet fait l'utilisation de certaines librairies de programmation externes.

- [Gson](https://github.com/google/gson): pour la sérialisation et la désérialisation de fichiers JSON.

- [Text IO](https://text-io.beryx.org/releases/latest/): pour faciliter les entrées et sorties en ligne de commande, et pour aider à la validation des entrées.

- [Bcrypt Java Library](https://github.com/patrickfav/bcrypt): pour le chiffrement et le déchiffrement des mots de passe des utilisateurs.

- [JUnit 5](https://junit.org/junit5/): Pour créer et exécuter des tests unitaires.

- [Mockito](https://site.mockito.org/): Pour aider à la création de mocks, stubs et spies.

- [JavaFX](https://openjfx.io/) Pour l'interface graphique.