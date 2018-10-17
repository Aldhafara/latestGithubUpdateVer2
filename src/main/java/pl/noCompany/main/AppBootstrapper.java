package pl.noCompany.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.noCompany.Main;
import pl.noCompany.controller.ViewController;


import java.net.*;
import java.util.List;

public class AppBootstrapper extends Application {


    private Stage stage;

    public static String[] informations(String login) throws Exception {



        String[] repos = new String[3];
        repos[0] = "Brak danych";
        repos[1] = "Brak danych";
        repos[2] = "Brak danych";
        URL url = new URL("https://github.com/" + login + "?tab=repositories");

        /**
         * PONIZSZY BLOK SPRAWDZA POLACZENIE Z ADRESEM URL
         */
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        int responseCode = huc.getResponseCode();

        if (responseCode == 404) {

            repos[0] = ("Uzytkownik " + login + " nie istnieje.");
            return repos;
        }else
        if ((responseCode >= 400) && (responseCode < 600)) {
            repos[0] = "ERROR " + responseCode;
            return repos;
        }


        Methods repositoryName = new Methods();

        List repoStringList = repositoryName.sourceWriter(url);


        System.out.println("___________________________________________");
        repositoryName.writeRepositoriesInConsole();



        if (repoStringList.size()<2)
        {
            repos[0] = "Uzytkownik " + login + " na puste repozytorium.";
            return repos;

        }

        repos[1] = (String) repoStringList.get(0);
        repos[2] = (String) repoStringList.get(1);

        return repos;
    }


    public static void startInWindow(String[] args) throws RuntimeException  {
        launch();
    }

    public void start (Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.stage.setTitle("Latest Github Update ver2.0");
        loadView();
    }

    private void loadView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Box.fxml"));

            VBox layout = (VBox) loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            ViewController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
