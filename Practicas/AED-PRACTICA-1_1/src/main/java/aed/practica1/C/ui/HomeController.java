package aed.practica1.C.ui;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.utils.Alertas;
import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Generador;
import aed.practica1.C.utils.Listar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
        Garaje.setGaraje(Generador.generarDatos(12));
        listController = new ListController();
        devolverController = new DevolverController();
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

    @FXML
    private void devolverVehiculos(){
        Turismo turismo;
        Camion camion;
        var vehiculoOptional = devolverController.show();
        if(vehiculoOptional.isPresent()){
            var vehiculoDevuelto = vehiculoOptional.get();
            Garaje.replace(vehiculoDevuelto);
            listController.update();
            if(vehiculoDevuelto instanceof Turismo) {
                turismo = (Turismo) vehiculoDevuelto;
                Alertas.success("Vehículo devuelto con éxito",String.format("Se devuelve el %s con matrícula %s con %d días.\n-> Cantidad a pagar: %.2f€","turismo",turismo.getMatricula(),turismo.getDiasAlquiler(),turismo.importeFinal()));
            }else{
                camion = (Camion) vehiculoDevuelto;
                Alertas.success("Vehículo devuelto con éxito",String.format("Se devuelve el %s con matrícula %s con %d días.\n-> Cantidad a pagar: %.2f€","camion",camion.getMatricula(),camion.getDiasAlquiler(),camion.importeFinal()));
            }
        }
        else{
            Alertas.showError("Operación no realizada","Formulario no completo.");
        }
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
            if(vehiculoAlquilado instanceof Turismo) Alertas.success("Coche alquilado con éxito",String.format("Total turismos: %d Alquilados: %s",Turismo.COCHES_TOTALES,Listar.contCochesAlquilados));
            else Alertas.success("Camión alquilado con éxito",String.format("Total camiones: %d Alquilados: %s",Camion.CAMIONES_TOTALES,Listar.contCamionesAlquilados));
            Garaje.replace(vehiculoAlquilado);
            listController.update();
        }
        else{
            Alertas.showError("Opeeración no realizada","Formulario no completo.");
        }

    }
}
