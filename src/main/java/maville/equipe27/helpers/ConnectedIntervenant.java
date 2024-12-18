package maville.equipe27.helpers;

import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Resident;

public class ConnectedIntervenant {
    private static ConnectedIntervenant instance;
    private Intervenant intervenant;

    private ConnectedIntervenant() { }

    public static ConnectedIntervenant getInstance() {
        if (instance == null) {
            instance = new ConnectedIntervenant();
        }

        return instance;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }
}
