package aed.practica1.A.utils;

import javafx.scene.control.Alert;

public class Alertas {
    public static void error(String errorContent){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Operación no realizada");
        a.setContentText(errorContent);
        a.showAndWait();
    }

    public static void success(String message){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Operación realizada con éxito.");
        a.setContentText(message);
        a.showAndWait();
    }
}
