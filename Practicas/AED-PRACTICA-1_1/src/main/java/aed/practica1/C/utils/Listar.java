package aed.practica1.C.utils;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Listar {
    private static final SecureRandom sr = new SecureRandom();

    private enum Islas{ TF, GC, LP, EH, LG, FV, LZ }

    private static Vehiculo generarVehiculo(){
        SecureRandom sr = new SecureRandom();
        var type = sr.nextInt(101);
        if(type > 50){
            var t = new Turismo(generarMatricula(),isAlquilado());
            t.setPrecioDia(generarPrecioDia());
            return t;
        }
        var c = new Camion(generarMatricula(),isAlquilado());
        c.setPrecioDia(generarPrecioDia());
        return c;
    }

    //Preparamos matrícula
    private static String generarMatricula(){
        StringBuilder matricula = new StringBuilder(getCodigoIsla() + "-" + sr.nextInt(10000) + "-");
        for (int i = 0; i < 3; i++) {
            char letra = (char)(sr.nextInt(25)+65);
            matricula.append(letra);
        }
        return matricula.toString();
    }

    private static String getCodigoIsla(){
        return Islas.values()[sr.nextInt(Islas.values().length)].name();
    }




    private static boolean isAlquilado(){
        return sr.nextBoolean();
    }


    private static double generarPrecioDia(){
        var precio = sr.nextDouble(131);
        return precio >= 20 ? precio : precio+20;
    }




    public static ArrayList<Vehiculo> generarDatos(int numDatos){
        var lista = new ArrayList<Vehiculo>();
        for (int i = 0; i < numDatos; i++) {
            lista.add(generarVehiculo());
        }
        return lista;
    }





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
                ###################
                """,Turismo.COCHES_TOTALES,contCochesAlquilados,Camion.CAMIONES_TOTALES,contCamionesAlquilados);
    }

    public static void mostrarInfo(){
        Garaje.getGaraje().forEach(e -> System.out.println(e.mostrarInformacion()));
    }



}
