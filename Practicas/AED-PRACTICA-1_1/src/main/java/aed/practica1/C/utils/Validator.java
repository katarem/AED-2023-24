package aed.practica1.C.utils;

import aed.practica1.C.exceptions.DiasInvalidosException;
import aed.practica1.C.exceptions.MatriculaInvalidaException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validateMatricula(String input) throws MatriculaInvalidaException{
        Pattern pattern = Pattern.compile("\\w{2}-\\d{4}-\\w{3}");
        Matcher m = pattern.matcher(input);
        if(!m.matches()) throw new MatriculaInvalidaException("La matrícula no coincide con el patrón requerido. Ejemplo: LP-2693-VLH");
    }
    public static int validateDiasAlquiler(String input) throws DiasInvalidosException{
        try{
            var dias = Integer.parseInt(input);
            if(dias < 0) throw new DiasInvalidosException("No existen los días negativos");
            return dias;
        } catch(NumberFormatException e){
            throw new DiasInvalidosException("Los días deben ser enteros.");
        }
    }




}
