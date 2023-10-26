package UD1.ej1_4;

import java.util.function.Function;

public interface ValidacionRegistroContacto extends Function<Contacto, ValidacionRegistroContacto.resultadoValidacion> {

    public enum resultadoValidacion{
        SATISFACTORIO,
        NUMERO_CONTACTO_NO_VALIDO,
        EMAIL_NO_VALIDO,
        NO_ES_ADULTO
    }



}
