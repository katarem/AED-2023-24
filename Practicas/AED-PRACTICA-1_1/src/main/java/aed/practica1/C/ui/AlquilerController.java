package aed.practica1.C.ui;

import aed.practica1.C.exceptions.OcupadosException;
import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.utils.Alertas;
import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Listar;
import aed.practica1.C.utils.Validador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlquilerController implements Initializable {

    private Dialog<Vehiculo> dialog = new Dialog<>();

    @FXML
    private DialogPane vista;

    @FXML
    private Label titleLabel, matriculaLabel;

    @FXML
    private TextField matriculaText, diasText;

    @FXML
    private Button matriculaButton, diasButton;

    private String matricula;
    private int diasAlquiler;

    private boolean isCoche;

    private List<Vehiculo> alquilerList;

    private Turismo coche;
    private Camion camion;

    private boolean validDate = false;
    public AlquilerController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_C/AlquilerView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialog.setDialogPane(vista);
        dialog.setResizable(false);
        dialog.setResultConverter(e -> {
            if(!validDate) return null;
            return isCoche ? coche : camion;
        });
    }
    public DialogPane getView() {
        return this.vista;
    }

    public void setOption(String option){
        matriculaLabel.setText("INDICAR MATRÍCULA DEL " + (option.equals("coches")?"COCHE":"CAMIÓN") + " CONTRATADO:");
        titleLabel.setText("ALQUILER DE " + option.toUpperCase() + ":");
        isCoche = option.equals("coches");
        alquilerList = getCochesOCamiones();
    }

    @FXML
    private void checkMatricula(){
        var garaje = getCochesOCamiones();
        try{
            Validador.validateMatricula(matriculaText.getText());
            Optional<Vehiculo> optionalVehiculo = alquilerList.stream().filter(e -> e.getMatricula().equals(matriculaText.getText())).findAny();
            if(optionalVehiculo.isEmpty()) throw new NoSuchElementException("No existe ese " + (isCoche ? "coche" : "camión") +
                    " en nuestra tienda");

            if(isCoche) {
                coche = (Turismo) optionalVehiculo.get();
                if(coche.isAlquilado()) throw new OcupadosException("El coche elegido está alquilado");
                Alertas.success("Coche seleccionado","El coche con matrícula " + coche.getMatricula() + " ha sido seleccionado correctamente");
            }
            else {
                camion = (Camion) optionalVehiculo.get();
                if(camion.isAlquilado()) throw new OcupadosException("El camión elegido está alquilado");
                Alertas.success("Camión seleccionado","El camión con matrícula " + camion.getMatricula() + " ha sido seleccionado correctamente");
            }

            matriculaButton.setDisable(true);
            matriculaText.setDisable(true);

            diasText.setDisable(false);
            diasButton.setDisable(false);
        } catch(NoSuchElementException e){
            Alertas.showError(e, "Matrícula inválida");
        } catch(OcupadosException e){
            Alertas.showError(e, "Error en alquiler");
        }
    }

    @FXML
    private void checkDiasAlquiler(){
        try {
            diasAlquiler = Validador.validateDias(diasText.getText());
            validDate = true;
            if (isCoche) {
                Listar.contCochesAlquilados++;
                coche.alquilar(diasAlquiler);}
            else {
                Listar.contCamionesAlquilados++;
                camion.alquilar(diasAlquiler);
            }
        } catch(NumberFormatException e){
            Alertas.showError(e, "Días inválidos");
        }
    }



    public Optional<Vehiculo> deposito(){
        return dialog.showAndWait();
    }

    private List<Vehiculo> getCochesOCamiones(){
        //nos devuelve una lista de los coches o camiones disponibles, para evitar coches falsos... etc
        var garaje = Garaje.getGaraje();
        if(isCoche) return garaje.stream().filter(e -> e instanceof Turismo).toList();
        else return garaje.stream().filter(e -> e instanceof Camion).toList();
    }




}
