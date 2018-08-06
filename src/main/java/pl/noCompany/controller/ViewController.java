package pl.noCompany.controller;

import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import pl.noCompany.main.AppBootstrapper;

import java.io.IOException;


public class ViewController {


    private AppBootstrapper main;
    private String[] values = {"Brak danych","Brak danych","Brak danych"};
    private Task copyWorker;




    @FXML
    private Label timeArea;
    @FXML
    private TextField userField;
    @FXML
    private Label repozytoriumArea;
    @FXML
    private Label ERRORLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button showMore;






    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(2000);
                    updateMessage("2000 milliseconds");
                    updateProgress(i + 1, 10);

                    System.out.println(progressBar.getProgress());
                }
                return true;
            }
        };
    }

    @FXML
    public void handleShowMore() throws Exception {

    }

    @FXML
    public void handleOk() throws Exception {

/*
        progressBar.setProgress(0);
      //  copyWorker = createWorker();

        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(copyWorker.progressProperty());

      //  new Thread(copyWorker).start();

*/

        search();



        if (!repozytoriumArea.getText().equals(" "))
            showMore.setVisible(true);
    }

    @FXML
    public void initialize(){
        repozytoriumArea.setText(" ");
        timeArea.setText(" ");
        ERRORLabel.setText(" ");
        showMore.setVisible(false);

        userField.setText("podaj login");
    }
    private void search() throws Exception {

        repozytoriumArea.setText(" ");
        timeArea.setText(" ");
        ERRORLabel.setText(" ");

        this.values = AppBootstrapper.informations(userField.getText());
       // System.out.println(values[0] + ";" + values[1] + ";" + values[2]);

        if (values[0].equals("Brak danych"))
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
            showMore.setVisible(false);
            alert.showAndWait();
        }
    }

    public ViewController() throws IOException {
    }


    public void setMain(AppBootstrapper main) throws Exception {

    }
}
