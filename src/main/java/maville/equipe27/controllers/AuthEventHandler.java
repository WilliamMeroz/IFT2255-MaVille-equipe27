package maville.equipe27.controllers;

import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AuthEventHandler implements PropertyChangeListener {
    private final IController residentController;
    private final IController intervenantController;

    public AuthEventHandler(IController residentController, IController intervenantController) {
        this.residentController = residentController;
        this.intervenantController = intervenantController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RoleChoices role = ((User) evt.getNewValue()).getRole();
        if (role == RoleChoices.RÉSIDENT) this.residentController.run();
        if (role == RoleChoices.INTERVENANT) this.intervenantController.run();
    }
}
