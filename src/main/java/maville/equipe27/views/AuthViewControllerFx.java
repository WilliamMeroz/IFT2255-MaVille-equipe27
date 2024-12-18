package maville.equipe27.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import maville.equipe27.controllers.AuthController;
import maville.equipe27.helpers.ConnectedIntervenant;
import maville.equipe27.helpers.ConnectedResident;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;

import java.io.IOException;

public class AuthViewControllerFx {

    private AuthController authController;

    @FXML
    private TextField email;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerButton;

    @FXML
    public void initialize() {
        authController = new AuthController();

        // Add an action listener to the login button
        loginButton.setOnAction(event -> handleLogin());
        registerButton.setOnAction(event -> openRegisterPage());
    }

    private void handleLogin() {
        String emailInput = email.getText();
        String passwordInput = password.getText();

        // Validate email
        if (!isValidEmail(emailInput)) {
            showAlert("Courriel invalide", "Donner un courriel valide.");
            email.setStyle("-fx-border-color: red;"); // Highlight the email field
            return;
        } else {
            email.setStyle(""); // Reset the style if valid
        }

        // Validate password
        if (!isValidPassword(passwordInput)) {
            showAlert("Mot de passe invalide", "Mot de passe doit être valide, plus de 8 caractères de long.");
            password.setStyle("-fx-border-color: red;"); // Highlight the password field
            return;
        } else {
            password.setStyle(""); // Reset the style if valid
        }

        // If both fields are valid, proceed with login logic
        performLogin(emailInput, passwordInput);
    }

    private void openRegisterPage() {
        switchScene("/register.fxml", "Inscription");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void performLogin(String email, String password) {
        User user = authController.login(email, password);
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CONNEXION IMPOSSIBLE");
            alert.setHeaderText(null);
            alert.setContentText("Courriel ou mot de passse invalide.");
            alert.showAndWait();
        } else {
            if (user instanceof Resident) {
                ConnectedResident.getInstance().setResident((Resident) user);
                switchScene("/residentMenu.fxml", "Menu résident");
            } else if (user instanceof Intervenant) {
                ConnectedIntervenant.getInstance().setIntervenant((Intervenant) user);
                switchScene("/intervenantMenu.fxml", "Menu intervenant");
            }
        }
    }

    private void switchScene(String resssource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resssource));
            Parent root = loader.load();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
