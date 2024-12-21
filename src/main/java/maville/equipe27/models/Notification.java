package maville.equipe27.models;

import java.util.Date;

public class Notification {
    String message;
    String associatedEmail;
    Date dateEmitted;

    public Notification(String message, String associatedEmail, Date dateEmitted) {
        this.message = message;
        this.associatedEmail = associatedEmail;
        this.dateEmitted = dateEmitted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAssociatedEmail() {
        return associatedEmail;
    }

    public void setAssociatedEmail(String associatedEmail) {
        this.associatedEmail = associatedEmail;
    }

    public Date getDateEmitted() {
        return dateEmitted;
    }

    public void setDateEmitted(Date dateEmitted) {
        this.dateEmitted = dateEmitted;
    }
}
