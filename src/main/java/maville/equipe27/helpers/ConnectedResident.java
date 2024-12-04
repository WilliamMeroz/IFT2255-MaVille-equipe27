package maville.equipe27.helpers;

import maville.equipe27.models.Resident;

public class ConnectedResident {
    private static ConnectedResident instance;
    private Resident resident;

    private ConnectedResident() { }

    public static ConnectedResident getInstance() {
        if (instance == null) {
            instance = new ConnectedResident();
        }

        return instance;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
