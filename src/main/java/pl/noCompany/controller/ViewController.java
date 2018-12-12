package pl.noCompany.controller;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import pl.noCompany.main.AppBootstrapper;
import pl.noCompany.main.Methods;

import java.io.IOException;


public class ViewController {


    @FXML
    private Label timeArea;
    @FXML
    private TextField userField;

    @FXML
    private Label repozytoriumArea;


    @FXML
    public void handleOk() throws Exception {

        search();

    }

    @FXML
    public void initialize(){
        repozytoriumArea.setText(" ");
        timeArea.setText(" ");

        userField.setText("podaj login");
    }

    private void search() throws Exception {

        repozytoriumArea.setText(" ");
        timeArea.setText(" ");

        String[] values = Methods.informations(userField.getText());

        if ("Brak danych".equals(values[0]))
        {
            repozytoriumArea.setText(values[1]);
            timeArea.setText(values[2] + " UTC");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nie znaleziono repozytorium");
            alert.setHeaderText(null);
            alert.setContentText(values[0]);
            alert.showAndWait();
        }
    }

    public ViewController() throws IOException, Exception {
    }


    public void setMain(AppBootstrapper main) throws Exception {

    }
}
