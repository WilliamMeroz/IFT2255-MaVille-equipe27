package maville.equipe27.controllers;

import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuthEventHandler implements PropertyChangeListener {
    private final ResidentController residentController;
    private final IntervenantController intervenantController;

    public AuthEventHandler(ResidentController residentController, IntervenantController intervenantController) {
        this.residentController = residentController;
        this.intervenantController = intervenantController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        User user = (User) evt.getNewValue();
        RoleChoices role = user.getRole();
        if (role == RoleChoices.RÉSIDENT) {
            this.residentController.handleConnectionEvent(new Resident(user));
            this.residentController.run();
        }
        if (role == RoleChoices.INTERVENANT) {
            this.intervenantController.handleConnectionEvent(new Intervenant(user));
            this.intervenantController.run();
        }
    }
}
