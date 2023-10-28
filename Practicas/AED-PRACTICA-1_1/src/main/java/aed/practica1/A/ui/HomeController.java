package aed.practica1.A.ui;

import aed.practica1.A.utils.Alertas;
import aed.practica1.A.utils.Almacen;
import aed.practica1.A.utils.Generador;
import aed.practica1.A.utils.ListarArticulos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private BorderPane vista;

    public HomeController(){
        try {
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_A/HomeView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarRevistas(){
        MostrarController mostrarController = new MostrarController();
        mostrarController.setContent("revistas");
        mostrarController.show();
    }

    @FXML
    private void mostrarBoletines(){
        MostrarController mostrarController = new MostrarController();
        mostrarController.setContent("boletines");
        mostrarController.show();
    }

    @FXML
    private void registrarRevista(){
        RegistrarRevistaController r = new RegistrarRevistaController();
        var resultado = r.show();
        if(resultado.isPresent()) {
            Almacen.add(resultado.get());
            Alertas.success("Revista agregada con éxito.");
        }
    }

    @FXML
    private void registrarBoletin(){
        RegistrarBoletinController r = new RegistrarBoletinController();
        var resultado = r.show();
        if(resultado.isPresent()){
            Almacen.add(resultado.get());
            Alertas.success("Boletín agregado con éxito.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Almacen.addAll(Generador.generarArticulos());

    }

    @FXML
    private void exit(){
        System.exit(0);
    }

    public BorderPane getView(){
        return vista;
    }

}
