package UD1.ej1_2.exceptions;

import java.util.InputMismatchException;

public class CodigoSeguridadInvalidoException extends InputMismatchException{
    public CodigoSeguridadInvalidoException() { super("[!] El código de seguridad social no sigue el formato XXX-XX-XXXX");}
}
