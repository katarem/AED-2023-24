package aed.practica1.C.ui.home;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.ui.DevolverController;
import aed.practica1.C.ui.ListController;
import aed.practica1.C.ui.alquiler.AlquilerController;
import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Listar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private BorderPane view;


    private ListController listController;

    private DevolverController devolverController;

    private Turismo coche;
    private Camion camion;

    //Controlador lee la vista para representarla
    public HomeController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_C/HomeView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Garaje.setGaraje(Listar.generarDatos(10));
        listController = new ListController();
        //devolverController = new DevolverController();
        listController.update();
    }

    public BorderPane getView(){
        return view;
    }

    @FXML
    private void exit(){
        System.exit(0);
    }

    @FXML
    private void listarVehiculos(){
        listController.show();
    }

    private void devolverVehiculos(){
        var vehiculoDevuelto = devolverController.show().orElseThrow();
        Garaje.replace(vehiculoDevuelto);
        listController.update();
        success("Vehículo devuelto con éxito","El vehículo con matrícula " + vehiculoDevuelto.getMatricula());
    }


    @FXML
    private void alquilarCoche(){
        alquilarVehiculo("coches");
    }
    @FXML
    private void alquilarCamion(){
        alquilarVehiculo("camiones");
    }


    private void alquilarVehiculo(String option){
        AlquilerController ac = new AlquilerController();
        ac.setOption(option);
        Optional<Vehiculo> o = ac.deposito();
        if(o.isPresent()){
            var vehiculoAlquilado = o.get();
            if(vehiculoAlquilado instanceof Turismo) coche = (Turismo) vehiculoAlquilado;
            else camion = (Camion) vehiculoAlquilado;
            Garaje.replace(vehiculoAlquilado);
            success("Alquiler exitoso","Ha alquilado el " + (coche==null ? "camión" : "coche") + " con matrícula " + vehiculoAlquilado.getMatricula() + " durante " +
                            (coche == null ? camion.getDiasAlquiler() : coche.getDiasAlquiler()) + " días.\n" + "El importe final será de " + String.format("%.2f€",(coche == null ? camion.importeFinal() : coche.importeFinal()))
                    );
            listController.update();
        }
    }

    private void success(String title, String msg){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(msg);
        a.showAndWait();
    }




}
