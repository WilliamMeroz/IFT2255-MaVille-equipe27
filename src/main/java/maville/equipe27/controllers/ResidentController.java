package maville.equipe27.controllers;

import maville.equipe27.models.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResidentController implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (((User) evt.getNewValue()).getRole().equals("RESIDENT")) {
            System.out.println("ON EST CONNECTÉ EN TANT QUE RÉSIDENT");
        }
    }
}
