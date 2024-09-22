package maville.equipe27.models;

public class Intervenant extends User {

    // Copy constructor.
    public Intervenant(User user) {
        super(user.getUsername(), user.getPassword(), user.getRole());
    }
}
