package aed.practica1.C.utils;

import javafx.scene.control.Alert;

public class Alertas {

    public static void success(String title, String msg){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(msg);
        a.showAndWait();
    }

    public static void showError(String title, String msg){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(msg);
        a.showAndWait();
    }

    public static void showError(Exception e, String title){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(title);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }
}
