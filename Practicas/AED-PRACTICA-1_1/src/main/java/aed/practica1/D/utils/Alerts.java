package aed.practica1.D.utils;

import javafx.scene.control.Alert;

public class Alerts {

    public static void error(String title, String msg){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static void info(String title,String msg){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setContentText(msg);
        a.showAndWait();
    }


}
