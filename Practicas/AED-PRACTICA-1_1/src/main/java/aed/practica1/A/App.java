package aed.practica1.A;

import aed.practica1.A.ui.HomeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HomeController home = new HomeController();
        stage.setTitle("gestión de artículos");
        stage.setScene(new Scene(home.getView()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
