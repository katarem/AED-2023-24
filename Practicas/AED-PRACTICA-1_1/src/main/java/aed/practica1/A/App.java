package aed.practica1.A;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("gestión de artículos");
        stage.setScene(new Scene(new Pane()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
