package maville.equipe27.views;

import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.Travail;
import maville.equipe27.models.RequeteTravail;
import org.beryx.textio.TextIO;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    public int showTravauxEnCours() {
        textIO.getTextTerminal().println("=== LISTE DES TRAVAUX EN COURS ===");

        textIO.getTextTerminal().println("1. Filter par type de travaux\n" +
                                         "2. Filter par quartier\n" +
                                         "3. Tous les travaux\n" +
                                         "4. Retour en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(1).read("Entrez votre choix: ");
        switch (choice) {
            case 1:
                return 111;
            case 2:
                return 112;
            case 3:
                return 113;
            default:
                return 0;
        }
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
        textIO.getTextTerminal().println("=== LISTE DES TRAVAUX À VENIR ===");

        textIO.getTextTerminal().println("""
                1. Filter par type de travaux
                2. Filter par quartier
                3. Tous les travaux
                4. Retour en arrière""");
        int choice = textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Entrez votre choix: ");
        return switch (choice) {
            case 1 -> 121;
            case 2 -> 122;
            case 3 -> 123;
            default -> 0;
        };
    }

    public int promptRechercheTravaux() {
        textIO.getTextTerminal().println("=== Recherche de travaux ===");

        textIO.getTextTerminal().println("1. Recherche par titre\n" +
                "2. Recherche par type de travaux\n" +
                "3. Recherche par quartier\n" +
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
        textIO.getTextTerminal().println("=== MENU DES REQUÊTES DE TRAVAIL ===");
        textIO.getTextTerminal().println("1. Soumettre une requête de travail\n" +
                "2. Consulter votre requête(s) (pas implémenté)\n" +
                "3. Retour en arrière");

        int choice = textIO.newIntInputReader().withMaxVal(3).withMinVal(1).read("Entrez votre choix: ");
        if (choice == 1) return 51;
        if (choice == 2) return 52;
        if (choice == 3) return 0;
        return choice;

    }

    public RequeteTravail promptSoumettreRequete() {
        String titreTravail = textIO.newStringInputReader().read("Entrez le titre du travail: ");
        TravauxTypes typeTravail = textIO.newEnumInputReader(TravauxTypes.class).read("Entrez le type de travail: ");
        LocalDate date = null;
        boolean success = false;
        while (!success) {
            try {
                String dateDebutStr = textIO.newStringInputReader().read("Entrez la date de début espéré: ");
                date = LocalDate.parse(dateDebutStr);
            } catch (DateTimeParseException _) {
                textIO.getTextTerminal().println("Entrez une date avec un format valide");
            }

            if (date.isBefore(LocalDate.now())) textIO.getTextTerminal().println("Entrez une date après la date courrante");
            else success = true;
        }

        String description = textIO.newStringInputReader().read("Entrez une description du travail:\n");
        RequeteTravail newRequete = new RequeteTravail(titreTravail, description, typeTravail, date);
        return newRequete;
    }

    public int showNewRequeteStatus(boolean status) {
        if (status)
            textIO.getTextTerminal().println("La requête a été enregistrée avec succès");
        else textIO.getTextTerminal().println("Un problème est servenu avec la création de la requête...");

        textIO.getTextTerminal().println("1. Retour en arrière. ");

        int choice = textIO.newIntInputReader().withMaxVal(1).withMinVal(1).read("Entrez votre choix: ");
        if (choice == 1) return 0;
        return choice;
    }

    public int promptTravaux() {
        textIO.getTextTerminal().println("=== MENU DES TRAVAUX ===");
        textIO.getTextTerminal().println("1. Travaux en cours. ");
        textIO.getTextTerminal().println("2. Travaux à venir (3 mois). ");
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

    public TravauxTypes promptTypeTravaux() {
        return textIO.newEnumInputReader(TravauxTypes.class).read("Entrez le type de travaux souhaité: ");
    }

    public String promptTravauxQuartier() {
       return textIO.newStringInputReader().withMinLength(1).read("Entrez le quartier: ");
    }

    public int showTravaux(List<Travail> travaux) {
        textIO.getTextTerminal().println("=== RÉSULTATS RECHERCHE ===");
        for (Travail t : travaux) {
            textIO.getTextTerminal().println(t.toString());
        }

        textIO.getTextTerminal().println("=== FIN RECHERCHE ===\n");

        textIO.getTextTerminal().println("1. Retour au menu principal.");
        int choice = textIO.newIntInputReader().withMinVal(1).withMaxVal(1).read("Entrez votre choix: ");

        return 0;
    }
}
