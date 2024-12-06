package maville.equipe27.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.RequeteTravail;

import java.time.LocalDate;
import java.util.List;

public class IntervenantMenuViewControllerFx {

    private IntervenantController intervenantController;

    @FXML
    private TableColumn<RequeteTravail, LocalDate> colRequetesDate;

    @FXML
    private TableColumn<RequeteTravail, String> colRequetesDesc;

    @FXML
    private TableColumn<RequeteTravail, String> colRequetesTitre;

    @FXML
    private TableColumn<RequeteTravail, TravauxTypes> colRequetesType;

    @FXML
    private TableView<RequeteTravail> tableViewRequetes;

    @FXML
    public void initialize() {
        intervenantController = new IntervenantController();

        colRequetesTitre.setCellValueFactory(new PropertyValueFactory<>("titreTravail"));
        colRequetesDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRequetesType.setCellValueFactory(new PropertyValueFactory<>("typeTravail"));
        colRequetesDate.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        List<RequeteTravail> requetes = intervenantController.consulterRequetes();
        if (!requetes.isEmpty()) {
            ObservableList<RequeteTravail> data = FXCollections.observableArrayList(requetes);
            tableViewRequetes.setItems(data);
        }
    }
}
