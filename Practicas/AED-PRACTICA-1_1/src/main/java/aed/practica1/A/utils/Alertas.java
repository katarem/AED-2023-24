package aed.practica1.A.utils;

import javafx.scene.control.Alert;

public class Alertas {


    public static void error(String errorContent){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Operación no realizada");
        a.setContentText(errorContent);
        a.showAndWait();
    }
}
