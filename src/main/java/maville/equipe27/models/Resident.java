package maville.equipe27.models;

public class Resident extends User {

    // Copy constructor.
    public Resident(User user) {
        super(user.getUsername(), user.getPassword(), user.getRole());
    }
}
