package pl.noCompany;

/**
 * Aldhafara Marek Nawrocki
 */


import pl.noCompany.main.AppBootstrapper;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) throws Exception {
        AppBootstrapper.startInWindow(args);
        //AppBootstrapper.startInConsole();
    }



    /*public static void main(String[] args) {

        launch(args);
    }

    public void start (Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.stage.setTitle("Moja aplikacja w JavaFX");
        loadView();
    }

    public void loadView(){
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

    }*/


}



