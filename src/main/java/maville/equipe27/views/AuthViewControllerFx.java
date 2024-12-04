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

import java.io.IOException;

public class AuthViewControllerFx {

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setTitle("Inscription");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        // Implement your login logic here
        System.out.println("Logging in with email: " + email + " and password: " + password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText(null);
        alert.setContentText("Welcome, " + email + "!");
        alert.showAndWait();
    }
}
