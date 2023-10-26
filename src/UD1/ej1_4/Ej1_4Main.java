package UD1.ej1_4;

import java.util.ArrayList;

public class Ej1_4Main {

    public static void main(String[] args) {
        ArrayList<Contacto> array = Validator.cargarContactos();
        Validator.validarContactos(array);
    }

}
