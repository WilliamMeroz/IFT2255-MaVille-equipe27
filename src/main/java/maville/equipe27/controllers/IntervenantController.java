package maville.equipe27.controllers;

import maville.equipe27.helpers.ConnectedIntervenant;
import maville.equipe27.helpers.NotifcationEmitter;
import maville.equipe27.helpers.ProjectDataStore;
import maville.equipe27.helpers.RequeteTravailDataStore;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Projet;
import maville.equipe27.models.RequeteTravail;
import maville.equipe27.views.IntervenantView;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Le contrôleur des intervenants. Cette classe gère les actions de l'intervenant
 * telles que la gestion des projets, la consultation des requêtes de travail et la gestion des notifications.
 */
public class IntervenantController implements IController {
    private Intervenant intervenant;
    private IntervenantView intervenantView;
    public RequeteTravailDataStore requeteTravailDataStore;
    public ProjectDataStore projectDataStore;
    public NotifcationEmitter notifcationEmitter;

    /**
     * Constructeur de la classe IntervenantController.
     * Initialise les différents objets nécessaires à la gestion des données.
     */
    public IntervenantController() {
        this.requeteTravailDataStore = new RequeteTravailDataStore("requetes.json");
        this.projectDataStore = new ProjectDataStore("projets.json");
        this.notifcationEmitter = new NotifcationEmitter("notifications.json");
    }

    /**
     * Constructeur de la classe IntervenantController avec une vue spécifique pour l'intervenant.
     *
     * @param intervenantView La vue associée à l'intervenant
     */
    public IntervenantController(IntervenantView intervenantView) {
        this.intervenantView = intervenantView;
        this.requeteTravailDataStore = new RequeteTravailDataStore("requetes.json");
    }

    /**
     * Gère l'événement de connexion de l'intervenant.
     *
     * @param intervenant L'intervenant qui se connecte
     */
    public void handleConnectionEvent(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Crée un nouveau projet.
     * Sauvegarde le projet et émet une notification si l'enregistrement du projet réussit.
     *
     * @param projet Le projet à créer
     * @return true si le projet est créé avec succès et la notification émise, false sinon
     */
    public boolean createNewProject(Projet projet) {
        boolean successNewProject = this.projectDataStore.saveProject(projet);
        boolean successNotifications = false;

        if (successNewProject) {
            String message = "Nouveau projet " + projet.getTitre() + " dans votre quartier!";
            successNotifications = this.notifcationEmitter.emit(projet, message);
        }

        return (successNewProject && successNotifications);
    }

    /**
     * Récupère la liste des projets de l'utilisateur (intervenant).
     *
     * @return Une liste des projets de l'intervenant
     */
    public ArrayList<Projet> getUserProjects() {
        return this.projectDataStore.getUserProjects();
    }

    /**
     * Met à jour un projet avec un nouveau titre.
     *
     * @param projet Le projet à mettre à jour
     * @param newTitle Le nouveau titre du projet
     * @return true si la mise à jour est réussie, false sinon
     */
    public boolean updateProject(Projet projet, String newTitle) {
        boolean successUpdateProjet = this.projectDataStore.updateProject(projet, newTitle);
        boolean successNotification = false;

        if (successUpdateProjet) {
            String message = "Mise à jour de projet " + projet.getTitre() + " effectuée!";
            successNotification = this.notifcationEmitter.emit(projet, message);
        }

        return (successUpdateProjet && successNotification);
    }

    public boolean soumettreCandiatureRequete(RequeteTravail requete) {
        return requeteTravailDataStore.addCandidate(requete, ConnectedIntervenant.getInstance().getIntervenant().getEmail());
    }

    public boolean acceptRequete(RequeteTravail requeteTravail) {
        return requeteTravailDataStore.acceptRequete(requeteTravail, ConnectedIntervenant.getInstance().getIntervenant());
    }

    /**
     * Méthode principale d'exécution du contrôleur.
     * Affiche le menu principal et gère les choix de l'utilisateur pour les différentes actions.
     */
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

    /**
     * Consulte et retourne la liste des requêtes de travail.
     *
     * @return Une liste des requêtes de travail
     */
    public List<RequeteTravail> consulterRequetes() {
        return this.requeteTravailDataStore.getRequetes();
    }

    /**
     * Affiche toutes les requêtes de travail en charge de l'intervenant.
     * Cette méthode charge les requêtes depuis un fichier JSON et les affiche.
     */
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
