package maville.equipe27.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterViewControllerFx {

    @FXML
    private Button registerChoiceResident;

    @FXML
    private Button registerChoiceIntervenant;

    @FXML
    public void initialize() {
        registerChoiceResident.setOnAction(event -> openRegisterPage("/registerResident.fxml"));
        registerChoiceIntervenant.setOnAction(event -> openRegisterPage("/registerIntervenant.fxml"));
    }

    private void openRegisterPage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            Stage stage = (Stage) registerChoiceResident.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
