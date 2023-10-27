package aed.practica1.C.exceptions;

public class OcupadosException extends NoSuchFieldException{
    public OcupadosException() {
    }

    public OcupadosException(String s) {
        super(s);
    }
}
