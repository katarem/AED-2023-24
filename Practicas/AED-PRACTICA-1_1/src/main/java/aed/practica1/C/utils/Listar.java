package aed.practica1.C.utils;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Listar {

    public static int contCochesAlquilados = 0, contCamionesAlquilados = 0;

    public static String mostrar(){
        if(contCochesAlquilados<1)
            setCochesAlquilados();
        if(contCamionesAlquilados<1)
            setCamionesAlquilados();
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

    private static void setCochesAlquilados(){
        var garaje = Garaje.getGaraje();
        contCochesAlquilados = (int) garaje.stream().filter(e -> e instanceof Turismo).filter(e -> ((Turismo) e).isAlquilado()).count();

    }

    private static void setCamionesAlquilados(){
        var garaje = Garaje.getGaraje();
        contCamionesAlquilados = (int) garaje.stream().filter(e -> e instanceof Camion).filter(e -> ((Camion) e).isAlquilado()).count();
    }

}
