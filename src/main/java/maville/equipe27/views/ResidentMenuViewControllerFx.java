package maville.equipe27.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import maville.equipe27.controllers.ResidentController;
import maville.equipe27.models.Entrave;

import java.util.List;

public class ResidentMenuViewControllerFx {

    private ResidentController residentController;

    @FXML
    private Button entravesButton;

    @FXML
    private RadioButton entravesRadioRue;

    @FXML
    private RadioButton entravesRadioTravail;

    @FXML
    private TableView<Entrave> entravesTableView;

    @FXML
    private TableColumn<Entrave, String> tableFromCol;

    @FXML
    private TableColumn<Entrave, String> tableLargObCol;

    @FXML
    private TableColumn<Entrave, String> tableStatusPisteCol;

    @FXML
    private TableColumn<Entrave, String> tableStatusTroitCol;

    @FXML
    private TableColumn<Entrave, String> tableToCol;

    @FXML
    private TableColumn<Entrave, String> tableTypeCol;

    @FXML
    private TextField entravesTextBox;

    @FXML
    private ToggleGroup ta;

    @FXML
    public void initialize() {
        residentController = new ResidentController();

        entravesButton.setOnAction(event -> handleSearch());
        entravesRadioRue.setOnAction(event -> entravesTextBox.setPromptText("Nom de rue"));
        entravesRadioTravail.setOnAction(event -> entravesTextBox.setPromptText("numéro de travail"));

        tableFromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableLargObCol.setCellValueFactory(new PropertyValueFactory<>("impactWidth"));
        tableStatusPisteCol.setCellValueFactory(new PropertyValueFactory<>("bikepathStatus"));
        tableStatusTroitCol.setCellValueFactory(new PropertyValueFactory<>("sidewalkStatus"));
        tableToCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        tableTypeCol.setCellValueFactory(new PropertyValueFactory<>("impactType"));
    }

    private void handleSearch() {
        String text = entravesTextBox.getText();
        if (text.isEmpty()) {
            showAlert("Recherche invalide", "Entrez une valeur de recherche");
            entravesTextBox.setStyle("-fx-border-color: red;"); // Highlight the email field
        } else entravesTextBox.setStyle("");

        if (entravesRadioTravail.isSelected()) {
            List<Entrave> entraves = residentController.consulterEntravesTravail(text);
            ObservableList<Entrave> data = FXCollections.observableArrayList(entraves);
            entravesTableView.setItems(data);
        }

        if (entravesRadioRue.isSelected()) {
            List<Entrave> entraves = residentController.consulterEntravesRue(text);
            ObservableList<Entrave> data = FXCollections.observableArrayList(entraves);
            entravesTableView.setItems(data);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
