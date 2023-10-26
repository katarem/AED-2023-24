package UD1.ej1_1.exceptions;

import java.util.InputMismatchException;

public class CodigoFueraDeRangoException extends InputMismatchException{

    public CodigoFueraDeRangoException(){
        super("[!] Sólo códigos entre 100 y 300");
    }

}
