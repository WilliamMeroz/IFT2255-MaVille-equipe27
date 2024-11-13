package maville.equipe27.views;

import maville.equipe27.models.Entrave;
import org.beryx.textio.TextIO;

import java.util.List;

public class ResidentView {
    private TextIO textIO;

    public ResidentView(TextIO textIO) {
        this.textIO = textIO;
    }

    public int promptMainMenu() {
        textIO.getTextTerminal().println("=== MENU PRINCIPAL ===");
        textIO.getTextTerminal().println("1. Menu des travaux\n" +
                                         "2. Notifications\n" +
                                         "3. Planification participative\n" +
                                         "4. Infos entraves\n" +
                                         "5. Requête de travail\n" +
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
        if (choice == 4) return 1;
        return choice;
    }

    public int promptEntraves() {
        textIO.getTextTerminal().println("=== Liste des entraves ===");
        textIO.getTextTerminal().println("1. Entraves associées à un travail\n" +
                "2. Entraves par rue\n" +
                "3. Retour en arrière");

        int choice = textIO.newIntInputReader().withMaxVal(3).withMinVal(1).read("Entrez votre choix: ");
        switch (choice) {
            case 1: return 41;
            case 2: return 42;
            case 3:
            default: return 0;
        }
    }

    public String promptEntravesRue() {
        return textIO.newStringInputReader().withMinLength(1).read("Entrez votre rue: ");
    }

    public String promptEntravesTravail() {
        textIO.getTextTerminal().println("Vous devez entrer le numéro d'identification du travail.\n" +
                "Vous pouvez obtenir ce numéro à partir du menu des travaux.");
        return textIO.newStringInputReader().withMinLength(1).read("Entrez le numéro d'ID: ").strip();
    }

    public int showEntraves(List<Entrave> entraves) {
        textIO.getTextTerminal().println("=== ENTRAVES TROUVÉES ===");
        if (entraves.isEmpty()) {
            textIO.getTextTerminal().println("Aucune entrave ne correspond à votre recherche.\n\n");
        }
        else {
            for (Entrave e : entraves)
                textIO.getTextTerminal().println(e.toString());
            textIO.getTextTerminal().println("=== FIN DES ENTRAVES ===");
        }

        textIO.getTextTerminal().println("1. Retour en arrière");
        textIO.newIntInputReader().withMinVal(1).withMaxVal(1).read("Entrez votre choix: ");

        return 0;
    }

    public int promptTravauxAVenir() {
        textIO.getTextTerminal().println("=== LISTE DES TRAVAUX À VENIR (Pas implémenté encore) ===");

        textIO.getTextTerminal().println("1. Filter par rue (pas implémenté)\n" +
                "2. Filter par type de travaux (pas implémenté)\n" +
                "3. Filter par quartier (pas implémenté)\n" +
                "4. Retour en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 1;
        return choice;
    }

    public int promptRechercheTravaux() {
        textIO.getTextTerminal().println("=== Recherche de travaux (Pas implémenté encore) ===");

        textIO.getTextTerminal().println("1. Recherche par titre (pas implémenté)\n" +
                "2. Recherche par type de travaux (pas implémenté)\n" +
                "3. Recherche par quartier (pas implémenté)\n" +
                "4. Retour en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 1;
        return choice;
    }

    public int promptNotifications() {
        textIO.getTextTerminal().println("=== MENU DES NOTIFICATIONS (Pas implémenté encore) ===");
        textIO.getTextTerminal().println("1. Mes notifications (pas implémenté)\n" +
                "2. S'abonner aux notifications d'une rue (pas implémenté)\n" +
                "3. S'abonner aux notifications d'un quartier (pas implémenté)\n" +
                "4. Désabonnement de notificatoins (pas implémenté)\n" +
                "5. Retour en arrière");

        int choice = textIO.newIntInputReader().withMaxVal(5).withMinVal(5).read("Entrez votre choix: ");
        if (choice == 5) return 0;
        return choice;
    }

    public int promptPlanificationParticipative() {
        textIO.getTextTerminal().println("=== MENU DE LA PLANIFICATION PARTICIPATIVE (Pas implémenté encore) ===");
        textIO.getTextTerminal().println("1. Plage(s) horaire(s) préfèré pour les travaux dans votre quartier (pas implémenté)\n" +
                "2. consulter les plages horaires préfèré des voisins (pas implémenté)\n" +
                "3. Forum des travaux (pas implémenté)\n" +
                "4. Retour en arrière");

        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 0;
        return choice;
    }

    public int promptSignalerProbleme() {
        textIO.getTextTerminal().println("=== MENU DE SINGNALEMENT DE PROBLÈME (Pas implémenté encore) ===");
        textIO.getTextTerminal().println("1. Retour en arrière. ");

        int choice = textIO.newIntInputReader().withMaxVal(1).withMinVal(1).read("Entrez votre choix: ");
        if (choice == 1) return 0;
        return choice;
    }

    public int promptRequeteTravail() {
        System.out.println("Entrez le numero désiré");
        System.out.println("1. Soumettre une requête de travaille");
        System.out.println("2. Consulter votre requête(s)");
        System.out.println("3. Retour");

        textIO.getTextTerminal().println("=== MENU DES REQUÊTES DE TRAVAIL (Pas implémenté encore) ===");
        textIO.getTextTerminal().println("1. Soumettre une requête de travaille (pas implémenté)\n" +
                "2. Consulter votre requête(s) (pas implémenté)\n" +
                "3. Retour en arrière");

        int choice = textIO.newIntInputReader().withMaxVal(3).withMinVal(3).read("Entrez votre choix: ");
        if (choice == 3) return 0;
        return choice;

    }

    public int promptTravaux() {
        textIO.getTextTerminal().println("=== MENU DES TRAVAUX ===");
        textIO.getTextTerminal().println("1. Travaux en cours. ");
        textIO.getTextTerminal().println("2. Travaux à venir. ");
        textIO.getTextTerminal().println("3. Rechercher travaux. ");
        textIO.getTextTerminal().println("4. Retour en arrière. ");

        int choice = textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Entrez votre choix: ");
        switch (choice) {
            case 1:
                return 11;
            case 2:
                return 12;
            case 3:
                return 13;
            default:
                return 0;
        }
    }
}
