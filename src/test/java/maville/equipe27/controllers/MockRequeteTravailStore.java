package maville.equipe27.controllers;

import java.util.ArrayList;
import java.util.List;

import maville.equipe27.helpers.RequeteTravailStore;
import maville.equipe27.models.RequeteTravail;

public class MockRequeteTravailStore extends RequeteTravailStore{
    
    public MockRequeteTravailStore() {
        super(null);
        
    }

    
    public String mockGetRequetes() {
        return "Liste de Requêtes";
    }

    @Override
    public Boolean saveRequetes(RequeteTravail requete) {
        return true;
    }
}
