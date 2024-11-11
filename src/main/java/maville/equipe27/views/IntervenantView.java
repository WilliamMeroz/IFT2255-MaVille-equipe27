package maville.equipe27.views;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class IntervenantView {
    TextIO textIO;

    public IntervenantView(TextIO textIO) {
        this.textIO = textIO;
    }

    public int promptMainMenu() {
        textIO.getTextTerminal().println("=== MENU PRINCIPAL ===");
        textIO.getTextTerminal().println("1. Consulter les travaux\n" +
                "2. Rechercher des travaux\n" +
                "3. Consulter la liste de requêtes de travail\n" +
                "4. Soumettre un nouveau projet de travaux\n" +
                "5. Mettre à jour les infos d'un chantier\n" +
                "6. Déconnecter\n");

        return textIO.newIntInputReader().withMinVal(1).withMaxVal(6).read("Entrez votre choix: ");
    }

    public int promptTravauxEnCours() {
        textIO.getTextTerminal().println("=== LISTE DES TRAVAUX EN COURS (Pas implémenté encore) ===");

        textIO.getTextTerminal().println("1. Filter par rue (pas implémenté)\n" +
                "2. Filter par type de travaux (pas implémenté)\n" +
                "3. Filter par quartier (pas implémenté)\n" +
                "4. Retour en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 0;
        return choice;
    }

    public int promptRechercheTravaux() {
        textIO.getTextTerminal().println("=== Recherche de travaux ===");
        textIO.getTextTerminal().println("1. Recherche par titre\n" +
                "2. Recherche par type\n" +
                "3. Recherche par quartier\n" +
                "4. Retour en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 0;
        return choice;
    }

    public int promptRequeteTravail() {
        textIO.getTextTerminal().println("=== Requêtes de travail ===");
        textIO.getTextTerminal().println("1. Soumettre sa candidature\n" +
                "2. Filtrer par type\n" +
                "3. Filtrer par quartier\n" +
                "4. Filtrer par date de début\n" +
                "5. Revenir en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(5).withMinVal(5).read("Entrez votre choix: ");
        if (choice == 5) return 0;
        return choice;
    }

    public int promptNouveauProjet() {
        textIO.getTextTerminal().println("=== Soumission de nouveau projet ===");
        textIO.getTextTerminal().println("1. Consulter les préférences des résidents\n" +
                "2. Revenir en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(2).withMinVal(2).read("Entrez votre choix: ");
        if (choice == 2) return 0;
        return choice;
    }

    public int promptModifChantier() {
        textIO.getTextTerminal().println("=== Modification de chantier ===");
        textIO.getTextTerminal().println("1. Mettre à jour description du projet\n" +
                "2. Mettre à jour la date de fin prévue\n" +
                "3. Changer le statut du projet\n" +
                "4. Revenir en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 0;
        return choice;
    }
}