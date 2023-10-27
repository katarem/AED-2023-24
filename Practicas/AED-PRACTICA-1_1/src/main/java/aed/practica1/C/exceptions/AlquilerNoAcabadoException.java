package aed.practica1.C.exceptions;

public class AlquilerNoAcabadoException extends NoSuchFieldException{

    public AlquilerNoAcabadoException(){ super(); }

    public AlquilerNoAcabadoException(String s) {
        super(s);
    }
}
