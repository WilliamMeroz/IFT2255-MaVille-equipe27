package maville.equipe27.controllers;

import maville.equipe27.models.Intervenant;
import maville.equipe27.views.IntervenantView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.beryx.textio.TextIO;
import java.io.FileReader;

public class IntervenantController implements IController {
    private Intervenant intervenant;
    private IntervenantView intervenantView;

    public IntervenantController(IntervenantView intervenantView) {
        this.intervenantView = intervenantView;
    }

    public void handleConnectionEvent(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    @Override
    public void run() {
        System.out.println("Intervenant:" + this.intervenant.getEmail());

        int choice = 0;
        while (true) {
            switch (choice) {
                case 0:
                    choice = intervenantView.promptMainMenu();
                    break;
                case 1:
                    afficherRequetesTravail();
                    choice = intervenantView.promptRequeteTravail();
                    break;
                case 2:
                    choice = intervenantView.promptNouveauProjet();
                    break;
                case 3:
                    choice = intervenantView.promptModifChantier();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }
    public void afficherRequetesTravail() {
        try {
            JSONParser parser = new JSONParser();
            Object file = parser.parse(new FileReader("requetes.json"));
            JSONArray requetes = (JSONArray) file;

            intervenantView.afficherRequetes(requetes);
        } catch (Exception e) {
            intervenantView.textIO.getTextTerminal().println("Erreur lors du chargement des requêtes : " + e.getMessage());
        }
    }
}
