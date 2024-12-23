package maville.equipe27.controllers;

import javafx.scene.Parent;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.helpers.*;
import maville.equipe27.models.*;
import maville.equipe27.views.ResidentView;
import maville.equipe27.models.RequeteTravail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Le contrôleur pour la gestion des actions d'un résident.
 * Cette classe permet de gérer les travaux, les requêtes, les entraves, les horaires,
 * et les notifications du résident en interagissant avec les modèles et les vues.
 */
public class ResidentController implements IController {

    private Resident resident;
    private ResidentView residentView;
    public HTTPRequestsHelper httpRequestsHelper;
    public RequeteTravailDataStore requeteTravailDataStore;
    public PrefHoraireDataStore prefHoraireDataStore;
    public NotifcationEmitter notificationEmitter;

    /**
     * Constructeur par défaut du contrôleur du résident.
     * Initialise les objets nécessaires à la gestion des données et des notifications.
     */
    public ResidentController() {
        this.httpRequestsHelper = new HTTPRequestsHelper();
        this.requeteTravailDataStore = new RequeteTravailDataStore("requetes.json");
        this.prefHoraireDataStore = new PrefHoraireDataStore("horaires.json");
        this.notificationEmitter = new NotifcationEmitter("notifications.json");
    }

    /**
     * Constructeur du contrôleur du résident avec la vue associée.
     *
     * @param residentView La vue associée au résident
     */
    public ResidentController(ResidentView residentView) {
        this.residentView = residentView;
        this.httpRequestsHelper = new HTTPRequestsHelper();
        this.requeteTravailDataStore = new RequeteTravailDataStore("requetes.json");
    }

    /**
     * Gère l'événement de connexion du résident.
     *
     * @param resident Le résident qui se connecte
     */
    public void handleConnectionEvent(Resident resident) {
        this.resident = resident;
    }

    /**
     * Consulte les entraves de la rue spécifiée.
     *
     * @param rue Le nom de la rue
     * @return La liste des entraves sur la rue
     */
    public List<Entrave> consulterEntravesRue(String rue) {
        return this.httpRequestsHelper.getEntravesByStreet(rue);
    }

    /**
     * Consulte les entraves liées à un travail spécifique.
     *
     * @param id L'identifiant de la requête de travail
     * @return La liste des entraves liées à ce travail
     */
    public List<Entrave> consulterEntravesTravail(String id) {
        return this.httpRequestsHelper.getEntravesByIdRequest(id);
    }

    /**
     * Consulte tous les travaux courants.
     *
     * @return La liste de tous les travaux en cours
     */
    public List<Travail> consulterTousLesTravauxCourrants() {
        return this.httpRequestsHelper.getCurrentTravaux();
    }

    /**
     * Consulte les travaux par quartier.
     *
     * @param quartier Le nom du quartier
     * @return La liste des travaux dans ce quartier
     */
    public List<Travail> consulterTravauxParQuartier(String quartier) {
        return this.httpRequestsHelper.getTravauxByQuartier(quartier);
    }

    /**
     * Consulte les travaux par type spécifié.
     *
     * @param type Le type de travail
     * @return La liste des travaux de ce type
     */
    public List<Travail> consulterTravauxParType(TravauxTypes type) {
        return this.httpRequestsHelper.getTravauxByType(type.toString());
    }

    /**
     * Consulte les travaux en fonction de critères de recherche.
     *
     * @param isFutur Indique si on cherche des travaux futurs (true) ou en cours (false)
     * @param typeRecherche Le type de recherche (1 = Tous les travaux, 2 = Par type, 3 = Par quartier)
     * @param filtre Le filtre pour la recherche (type de travail, quartier, etc.)
     * @return La liste des travaux correspondant aux critères
     */
    public List<Travail> consulterTravaux(boolean isFutur, int typeRecherche, String filtre) {
        List<Travail> travaux = new ArrayList<>();

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

    /**
     * Consulte les travaux futurs.
     *
     * @return La liste des travaux futurs
     */
    public List<Travail> consulterFutursTravaux() {
        return this.httpRequestsHelper.getFutureTravaux();
    }

    /**
     * Consulte les travaux futurs dans un quartier donné.
     *
     * @param quartier Le nom du quartier
     * @return La liste des travaux futurs dans ce quartier
     */
    public List<Travail> consulterFutursTravauxParQuartier(String quartier) {
        return this.httpRequestsHelper.getFutureTravauxByQuartier(quartier);
    }

    /**
     * Consulte les travaux futurs d'un type spécifié.
     *
     * @param type Le type de travail
     * @return La liste des travaux futurs de ce type
     */
    public List<Travail> consulterFutursTravauxParType(TravauxTypes type) {
        return this.httpRequestsHelper.getFutureTravauxByType(type.toString());
    }

    /**
     * Sauvegarde une nouvelle requête de travail.
     *
     * @param requeteTravail La requête de travail à sauvegarder
     * @return true si la requête est sauvegardée avec succès, false sinon
     */
    public boolean saveRequest(RequeteTravail requeteTravail) {
        return requeteTravailDataStore.saveRequete(requeteTravail);
    }

    /**
     * Sauvegarde un nouvel horaire de préférence.
     *
     * @param prefHoraire L'horaire de préférence à sauvegarder
     * @return true si l'horaire est sauvegardé avec succès, false sinon
     */
    public boolean saveNewHoraire(PrefHoraire prefHoraire) {
        return prefHoraireDataStore.saveHoraire(prefHoraire);
    }

    /**
     * Récupère les horaires de préférence pour un quartier donné.
     *
     * @param quartier Le nom du quartier
     * @return La liste des horaires de préférence pour ce quartier
     */
    public List<PrefHoraire> getHorairesByQuartier(String quartier) {
        return prefHoraireDataStore.getHorairesByQuartier(quartier);
    }

    /**
     * Récupère l'horaire de préférence pour un utilisateur donné par son email.
     *
     * @param email L'email de l'utilisateur
     * @return Un horaire de préférence, s'il existe
     */
    public Optional<PrefHoraire> getHoraireFromUser(String email) {
        return prefHoraireDataStore.getHoraireFromEmail(email);
    }

    /**
     * Récupère les requêtes de travail d'un utilisateur donné par son email.
     *
     * @param email L'email de l'utilisateur
     * @return La liste des requêtes de travail de cet utilisateur
     */
    public List<RequeteTravail> getUserRequests(String email) {
        return this.requeteTravailDataStore.getRequetesByEmail(email);
    }

    /**
     * Récupère les notifications pour l'utilisateur connecté.
     *
     * @return La liste des notifications pour l'utilisateur connecté
     */
    public List<Notification> getNotifications() {
        return this.notificationEmitter.getNotificationsForUser(ConnectedResident.getInstance().getResident());
    }

    public boolean acceptSubmission(String intervenantCourriel) {
        this.requeteTravailDataStore.acceptRequest(intervenantCourriel);
        return false;
    }

    /**
     * Exécute la logique principale du contrôleur en affichant les menus et gérant les choix de l'utilisateur.
     */
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
