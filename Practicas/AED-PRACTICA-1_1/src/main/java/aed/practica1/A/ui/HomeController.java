package aed.practica1.A.ui;

import aed.practica1.A.utils.Almacen;
import aed.practica1.A.utils.Generador;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    public HomeController(){
        try {
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_A/HomeView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    private void mostrarRevistas(){
        MostrarController mostrarController = new MostrarController();
        mostrarController.setContent("revistas");
        mostrarController.show();
    }

    private void mostrarBoletines(){
        MostrarController mostrarController = new MostrarController();
        mostrarController.setContent("boletines");
        mostrarController.show();
    }

    private void registrarRevista(){






    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Almacen.setAlmacen(Generador.generarArticulos());

    }
}
