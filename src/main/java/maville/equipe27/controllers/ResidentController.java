package maville.equipe27.controllers;

import javafx.scene.Parent;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.helpers.HTTPRequestsHelper;
import maville.equipe27.helpers.PrefHoraireDataStore;
import maville.equipe27.helpers.RequeteTravailDataStore;
import maville.equipe27.models.*;
import maville.equipe27.views.ResidentView;
import maville.equipe27.models.RequeteTravail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResidentController implements IController {

    private Resident resident;
    private ResidentView residentView;
    public HTTPRequestsHelper httpRequestsHelper;
    public RequeteTravailDataStore requeteTravailDataStore;
    public PrefHoraireDataStore prefHoraireDataStore;

    public ResidentController() {
        this.httpRequestsHelper = new HTTPRequestsHelper();
        this.requeteTravailDataStore = new RequeteTravailDataStore("requetes.json");
        this.prefHoraireDataStore = new PrefHoraireDataStore("horaires.json");
    }

    public ResidentController(ResidentView residentView) {
        this.residentView = residentView;
        this.httpRequestsHelper = new HTTPRequestsHelper();
        this.requeteTravailDataStore = new RequeteTravailDataStore("requetes.json");
    }

    public void handleConnectionEvent(Resident resident) {
        this.resident = resident;
    }

    public List<Entrave> consulterEntravesRue(String rue) {
        return this.httpRequestsHelper.getEntravesByStreet(rue);
    }

    public List<Entrave> consulterEntravesTravail(String id) {
        return this.httpRequestsHelper.getEntravesByIdRequest(id);
    }

    public List<Travail> consulterTousLesTravauxCourrants() {
        return this.httpRequestsHelper.getCurrentTravaux();
    }

    public List<Travail> consulterTravauxParQuartier(String quartier) {
        return this.httpRequestsHelper.getTravauxByQuartier(quartier);
    }

    public List<Travail> consulterTravauxParType(TravauxTypes type) {
        return this.httpRequestsHelper.getTravauxByType(type.toString());
    }

    public List<Travail> consulterTravaux(boolean isFutur, int typeRecherche, String filtre) {
        List<Travail> travaux = new ArrayList<>();

        System.out.println(isFutur);

        // Tous les travaux
        if (typeRecherche == 1) {
            if (isFutur)
                travaux = httpRequestsHelper.getFutureTravaux();
            else
                travaux = httpRequestsHelper.getCurrentTravaux();
        }

        // Les travaux par type
        if (typeRecherche == 2) {
            if (isFutur)
                travaux = httpRequestsHelper.getFutureTravauxByType(filtre);
            else
                travaux = httpRequestsHelper.getTravauxByType(filtre);
        }

        // Les travaux par quartier
        if (typeRecherche == 3) {
            if (isFutur)
                travaux = httpRequestsHelper.getFutureTravauxByQuartier(filtre);
            else
                travaux = httpRequestsHelper.getTravauxByQuartier(filtre);
        }

        return travaux;
    }

    public List<Travail> consulterFutursTravaux() {
        return this.httpRequestsHelper.getFutureTravaux();
    }

    public List<Travail> consulterFutursTravauxParQuartier(String quartier) {
        return this.httpRequestsHelper.getFutureTravauxByQuartier(quartier);
    }

    public List<Travail> consulterFutursTravauxParType(TravauxTypes type) {
        return this.httpRequestsHelper.getFutureTravauxByType(type.toString());
    }

    public boolean saveRequest(RequeteTravail requeteTravail) {
        return requeteTravailDataStore.saveRequete(requeteTravail);
    }

    public boolean saveNewHoraire(PrefHoraire prefHoraire) {
        return prefHoraireDataStore.saveHoraire(prefHoraire);
    }

    public List<PrefHoraire> getHorairesByQuartier(String quartier) {
        return prefHoraireDataStore.getHorairesByQuartier(quartier);
    }

    public Optional<PrefHoraire> getHoraireFromUser(String email) {
        return prefHoraireDataStore.getHoraireFromEmail(email);
    }

    public List<RequeteTravail> getUserRequests(String email) {
        return this.requeteTravailDataStore.getRequetesByEmail(email);
    }

    @Override
    public void run() {
        System.out.println("Résident: " + this.resident.getEmail());

        int choice = 0;
        while (true) {
            switch (choice) {
                case 0:
                    choice = residentView.promptMainMenu();
                    break;
                case 1:
                    choice = residentView.promptTravaux();
                    break;
                case 11:
                    choice = residentView.showTravauxEnCours();
                    break;
                case 111:
                    TravauxTypes typeCourrant = residentView.promptTypeTravaux();
                    choice = residentView.showTravaux(consulterTravauxParType(typeCourrant));
                    break;
                case 112:
                    String quartierCourrant = residentView.promptTravauxQuartier();
                    choice = residentView.showTravaux(consulterTravauxParQuartier(quartierCourrant));
                    break;
                case 113:
                    choice = residentView.showTravaux(consulterTousLesTravauxCourrants());
                    break;
                case 12:
                    choice = residentView.promptTravauxAVenir();
                    break;
                case 121:
                    TravauxTypes typeFuture = residentView.promptTypeTravaux();
                    choice = residentView.showTravaux(consulterFutursTravauxParType(typeFuture));
                    break;
                case 122:
                    String quartierFuture = residentView.promptTravauxQuartier();
                    choice = residentView.showTravaux(consulterFutursTravauxParQuartier(quartierFuture));
                    break;
                case 123:
                    choice = residentView.showTravaux(consulterFutursTravaux());
                    break;
                case 13:
                    choice = residentView.promptRechercheTravaux();
                    break;
                case 2:
                    choice = residentView.promptNotifications();
                    break;
                case 3:
                    choice = residentView.promptPlanificationParticipative();
                    break;
                case 4:
                    choice = residentView.promptEntraves();
                    break;
                case 41:
                    String id = residentView.promptEntravesTravail();
                    choice = residentView.showEntraves(consulterEntravesTravail(id));
                    break;
                case 42:
                    String rue = residentView.promptEntravesRue();
                    choice = residentView.showEntraves(consulterEntravesRue(rue));
                    break;
                case 5:
                    choice = residentView.promptRequeteTravail();
                    break;
                case 51:
                    RequeteTravail requeteTravail = residentView.promptSoumettreRequete();
                    boolean success = this.requeteTravailDataStore.saveRequete(requeteTravail);
                    choice = residentView.showNewRequeteStatus(success);
                    break;
                case 52:
                    choice = 5;
                    break;
                case 6:
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}
