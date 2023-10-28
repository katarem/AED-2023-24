package aed.practica1.D.utils;

import java.io.File;
import java.nio.file.Files;

public class Checker {


    public static boolean isFileCreated(File f){
        try{
            if(!f.exists()) f.createNewFile();
            return true;
        } catch(Exception e){
            Alerts.error("Error en fichero",e.getMessage());
            return false;
        }
    }

    public static File getDefaultDirectory(){
        try{
            return new File(System.getenv("USERPROFILE")+"\\Desktop");
        } catch(NullPointerException e){
            Alerts.error("Error al leer ubicación","Esa ubicación no existe");
            return null;
        }
    }

    public static String getFileContent(File fichero){
        try{
            return Files.readString(fichero.toPath());
        } catch(Exception e){
            Alerts.error("Error al obtener el contenido del fichero", e.getMessage());
            return null;
        }
    }



}
