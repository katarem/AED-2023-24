package aed.practica1.D;

import aed.practica1.D.ui.HomeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        App.stage = stage;
        HomeController homeController = new HomeController();
        App.stage.setScene(new Scene(homeController.getView()));
        App.stage.setTitle("Ficheros");
        App.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
