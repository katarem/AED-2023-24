package aed.practica1.C.utils;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.ui.ListController;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean para compartir almacenamiento entre las diferentes vistas
 */
public class Garaje {

    private static List<Vehiculo> garaje = new ArrayList<>();

    public static List<Vehiculo> getGaraje() {
        return garaje;
    }

    public static void setGaraje(List<Vehiculo> garaje) {
        Garaje.garaje = garaje;
    }


    //Por si hiciesen falta estas funciones
    public static void add(Vehiculo v){
        garaje.add(v);
    }
    public static void remove(Vehiculo v){
        garaje.remove(v);
    }

    public static void replace(Vehiculo v){
        var anterior = garaje.stream().filter(vg -> vg.getMatricula().equals(v.getMatricula())).findAny().orElseThrow();
        var pos = garaje.indexOf(anterior);
        garaje.remove(anterior);
        garaje.add(pos,v); // con esto conseguimos que no pierda su posición original
    }


}
