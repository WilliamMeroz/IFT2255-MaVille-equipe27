package maville.equipe27.controllers;

import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Cette classe écoute les événements de connexion (via PropertyChangeListener) et
 * redirige l'utilisateur connecté vers son contrôleur approprié (Resident ou Intervenant).
 */
public class AuthEventHandler implements PropertyChangeListener {
    private final ResidentController residentController; // Le contrôleur pour les résidents
    private final IntervenantController intervenantController; // Le contrôleur pour les intervenants

    /**
     * Constructeur de la classe AuthEventHandler. L'instance reçoit les contrôleurs
     * pour les résidents et les intervenants.
     */
    public AuthEventHandler(ResidentController residentController, IntervenantController intervenantController) {
        this.residentController = residentController;
        this.intervenantController = intervenantController;
    }

    /**
     * Méthode appelée lors d'un changement de propriété (lorsqu'un utilisateur se connecte).
     * Redirige l'utilisateur vers le contrôleur approprié selon son type (Resident ou Intervenant).
     *
     * @param evt L'événement de changement de propriété qui contient l'utilisateur connecté.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        User user = (User) evt.getNewValue(); // Récupération de l'utilisateur connecté

        // Si l'utilisateur est un résident, on redirige vers le ResidentController
        if (user instanceof Resident) {
            this.residentController.handleConnectionEvent((Resident) user);
            this.residentController.run();
        }

        // Si l'utilisateur est un intervenant, on redirige vers le IntervenantController
        if (user instanceof Intervenant) {
            this.intervenantController.handleConnectionEvent((Intervenant) user);
            this.intervenantController.run();
        }
    }
}
