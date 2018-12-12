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
