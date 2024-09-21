package maville.equipe27.controllers;

import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IntervenantController implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (((User) evt.getNewValue()).getRole() == RoleChoices.INTERVENANT) {
            System.out.println("ON EST CONNECTÉ EN TANT QU'INTERVENANT");
        }
    }
}
