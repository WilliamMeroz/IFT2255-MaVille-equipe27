package maville.equipe27.views;

import org.beryx.textio.TextIO;
import com.google.gson.Gson;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.*;

import java.io.FileReader;

public class IntervenantView {
    public TextIO textIO;

    public IntervenantView(TextIO textIO) {
        this.textIO = textIO;
    }

    public int promptMainMenu() {
        textIO.getTextTerminal().println("=== MENU PRINCIPAL ===");
        textIO.getTextTerminal().println("1. Consulter la liste de requêtes de travail\n" +
                "2. Soumettre un nouveau projet de travaux\n" +
                "3. Mettre à jour les infos d'un chantier\n" +
                "4. Déconnecter\n");

        return textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Entrez votre choix: ");
    }

    public int promptRequeteTravail() {
        textIO.getTextTerminal().println("=== Requêtes de travail ===");
        textIO.getTextTerminal().println("1. Soumettre sa candidature (pas implémenté)\n" +
                "2. Filtrer par type (pas implémenté)\n" +
                "3. Filtrer par quartier (pas implémenté)\n" +
                "4. Filtrer par date de début (pas implémenté)\n" +
                "5. Revenir en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(5).withMinVal(5).read("Entrez votre choix: ");
        if (choice == 5) return 0;
        return choice;
    }
    public void afficherRequetes(JSONArray requetes) {
        textIO.getTextTerminal().println("Liste des requêtes disponibles :");
        for (Object obj : requetes) {
            JSONObject requete = (JSONObject) obj;
            textIO.getTextTerminal().println("- Titre: " + requete.get("titre"));
            textIO.getTextTerminal().println("  Description: " + requete.get("description"));
            textIO.getTextTerminal().println("  Type: " + requete.get("type"));
            textIO.getTextTerminal().println("  Date souhaitée: " + requete.get("date"));
        }
    }
    public int promptNouveauProjet() {
        textIO.getTextTerminal().println("=== Soumission de nouveau projet (pas implémenté) ==");
        textIO.getTextTerminal().println("1. Consulter les préférences des résidents (pas implémenté)\n" +
                "2. Revenir en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(2).withMinVal(2).read("Entrez votre choix: ");
        if (choice == 2) return 0;
        return choice;
    }

    public int promptModifChantier() {
        textIO.getTextTerminal().println("=== Modification de chantier (pas implémenté) ===");
        textIO.getTextTerminal().println("1. Mettre à jour description du projet (pas implémenté)\n" +
                "2. Mettre à jour la date de fin prévue (pas implémenté)\n" +
                "3. Changer le statut du projet (pas implémenté)\n" +
                "4. Revenir en arrière");
        int choice = textIO.newIntInputReader().withMaxVal(4).withMinVal(4).read("Entrez votre choix: ");
        if (choice == 4) return 0;
        return choice;
    }
}
