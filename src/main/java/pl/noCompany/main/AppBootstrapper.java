package pl.noCompany.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.noCompany.Main;
import pl.noCompany.controller.ViewController;


import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Scanner;

public class AppBootstrapper extends Application {

    private Stage stage;
    private VBox layout;

    public static void startInConsole() throws Exception {

        String login = login();
        String[] values = informations(login);

        if (values[0]==null)
        System.out.println("Ostatnio zaktualizowanym repozytorium Uzytkownika "
                + login + " jest: << " + values[1]
                + " >>; zaktualizowano: " + values[2] + " UTC");
        else
            System.out.println(values[0]);

        deleteFile(new File ("src\\main\\resources\\source.txt"));

    }


    public static String[] informations(String login) throws Exception {

        Methods methods = new Methods();


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

        if (huc.getResponseCode() == 404) {

            repos[0] = ("Uzytkownik " + login + " nie istnieje.");
            return repos;
        }
        if ((huc.getResponseCode() != 404) && (huc.getResponseCode() >= 400) && (huc.getResponseCode() < 600)) {
            repos[0] = "ERROR " + huc.getResponseCode();
            return repos;
        }

        Methods repositoryName = new Methods();

        List<String> repos2 = repositoryName.sourceWriter(url);

        if (repos2.size()<2)
        {
            repos[0] = "Uzytkownik " + login + " na puste repozytorium.";
            return repos;

        }

        repos[1] = repos2.get(0);
        repos[2] = repos2.get(1);


        return repos;
    }

    private static void deleteFile(File file) {

        if(file.delete()){
            System.out.println("Skasowano plik tymczasowy: " + file.getName());
        }else{
            System.out.println("Operacja kasowania pliku tymczasowego nie powiodla sie.");
        }
    }

    private static String login(){
        System.out.println("Podaj login Uzytkownika, ktorego repozytorium chcesz sprawdzic: ");
        Scanner reader = new Scanner(System.in);
        String login = reader.nextLine();
        //System.out.println("Podales login: " + login);
        return login;
    }

    public static void startInWindow(String[] args) throws RuntimeException  {
        launch();
        deleteFile(new File ("source.txt"));
    }

    public void start (Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.stage.setTitle("Moja aplikacja w JavaFX");
        loadView();
    }

    private void loadView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Box.fxml"));

            layout = (VBox) loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            ViewController controller = loader.getController();
            controller.setMain(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Stage getStage() {
        return stage;
    }

    public void loadPersonNew(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/newPerson.fxml"));

            VBox window = (VBox) loader.load();



            Stage editStage = new Stage();
            editStage.setTitle("Dodaj osobę");
            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
