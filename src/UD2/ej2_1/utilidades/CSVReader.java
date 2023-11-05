package UD2.ej2_1.utilidades;

import UD2.ej2_1.modelo.Estudiante;
import UD2.ej2_1.modelo.Fecha;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class CSVReader {

    private static Logger logger;

    static {
        logger = Logger.getLogger(CSVReader.class.getName());
    }

    public static String CSVPATH;
    public static String BINARIOPATH;

    private static ArrayList<Estudiante> estudiantes;

    public static void readCSV(){
        estudiantes = getEstudiantes();
        logger.info("MUESTRA DE DATOS DE CSV");
        System.out.println("DNI             NOMBRE               EDAD    DIA     MES     AÑO");
        estudiantes.forEach(System.out::println);
    }


    private static ArrayList<Estudiante> getEstudiantes(){
        try{
            logger.info("EMPIEZA LECURA DE CSV");
            List<String> alumnos = Files.readAllLines(Path.of(CSVPATH));
            ArrayList<Estudiante> estudianes = new ArrayList<>();
            alumnos.forEach(entradaAlumno -> {
                //Si tiene el orden que debería...
                var datos = entradaAlumno.split(",");
                var dni = datos[0];
                var nombre = datos[1];
                var edad = Integer.parseInt(datos[2]);
                var fechaMatricula = new Fecha(Integer.parseInt(datos[3]),Integer.parseInt(datos[4]),Integer.parseInt(datos[5]));
                estudianes.add(new Estudiante(dni,nombre,edad,fechaMatricula));
                logger.info("ESTUDIANTE AGREGADO CON ÉXITO");
            });
            logger.info("FINALIZA LECTURA DE CSV!");
            return estudianes;
        } catch(IOException e){
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void writeBinario(){
        try{
            logger.info("EMPIEZA ESCRITURA BINARIO...");
            var fis = new FileOutputStream(BINARIOPATH);
            var os = new ObjectOutputStream(fis);
            os.writeObject(estudiantes);
            os.flush();
            os.close();
            fis.close();
            logger.info("TERMINA ESCRITURA BINARIO!");
        } catch(IOException e){
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void readBinario(){
        try{
            logger.info("EMPIEZA LECTURA BINARIO...");
            var fis = new FileInputStream(BINARIOPATH);
            var os = new ObjectInputStream(fis);
            var estudiantesObtenidos = (List<Estudiante>)os.readObject();
            os.close();
            fis.close();
            logger.info("TERMINA LECTURA BINARIO!");
            logger.info("MUESTRA DE DATOS DE BINARIO");
            System.out.println("DNI             NOMBRE               EDAD    DIA     MES     AÑO");
            estudiantesObtenidos.forEach(System.out::println);
        } catch(ClassNotFoundException | IOException e){
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    //DEBUG ONLY
    public static void deleteBinario(){
        try{
            File f = new File(BINARIOPATH);
            var eliminado = f.delete();
            if(eliminado) logger.info("Se ha eliminado el archivo con éxito");
            else logger.warning("No se eliminó el archivo");
        } catch(NullPointerException | SecurityException e){
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
