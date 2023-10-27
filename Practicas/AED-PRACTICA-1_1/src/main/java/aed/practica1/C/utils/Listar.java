package aed.practica1.C.utils;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Listar {

    public static String mostrar(){
        var garaje = Garaje.getGaraje();
        var contCochesAlquilados = garaje.stream().filter(e -> e instanceof Turismo).filter(e -> ((Turismo) e).isAlquilado()).count();
        var contCamionesAlquilados = garaje.stream().filter(e -> e instanceof Camion).filter(e -> ((Camion) e).isAlquilado()).count();
        return String.format("""
                ######RESUMEN######
                --> TOTAL COCHES = %d
                --> COCHES ALQUILADOS = %d
                --> TOTAL CAMIONES = %d
                --> CAMIONES ALQUILADOS = %d
                ##################
                """,Turismo.COCHES_TOTALES,contCochesAlquilados,Camion.CAMIONES_TOTALES,contCamionesAlquilados);
    }

    public static void mostrarInfo(){
        Garaje.getGaraje().forEach(e -> System.out.println(e.mostrarInformacion()));
    }



}
