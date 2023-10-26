package UD1.ej1_4;

import UD1.ej1_4.exceptions.RegistroInvalidoException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static ArrayList<Contacto> cargarContactos(){
        return new ArrayList<Contacto>(
                List.of(
                        new Contacto("Cristian","cristianEmail.com","+34 693 12 03 12", LocalDate.of(2003,8,13)),
                        new Contacto("Pedro","pedro@email.com","+34 924 21 45 22", LocalDate.of(1999,12,12)),
                        new Contacto("Laura","laura@email.com","+34 942 29 42 13",LocalDate.of(2010,10,20)),
                        new Contacto("Maria","maria@email.com","+34 952 19 85 72",LocalDate.of(2001,9,17)),
                        new Contacto("Paco", "paco@email.com" ,"+99 213 41 51 62" ,LocalDate.of(1990,1,1)),
                        new Contacto("Lucia","lucia@email.com","+34 941 93 85 71",LocalDate.of(2000,6,3))
                )
        );
    }

    public static void validarContactos(ArrayList<Contacto> array){
        ValidacionRegistroContacto estadoRegistro = (Contacto c) -> {
            if(!c.getEmail().contains("@"))  return ValidacionRegistroContacto.resultadoValidacion.EMAIL_NO_VALIDO;
            else if(Period.between(c.getNacimiento(), LocalDate.now()).getYears() < 18) return ValidacionRegistroContacto.resultadoValidacion.NO_ES_ADULTO;
            else if(!c.getTelefono().split(" ")[0].equals("+34")) return ValidacionRegistroContacto.resultadoValidacion.NUMERO_CONTACTO_NO_VALIDO;
            else return ValidacionRegistroContacto.resultadoValidacion.SATISFACTORIO;
        };
        array.forEach(c -> {
            try{
                var value = estadoRegistro.apply(c);
                var numContacto = array.indexOf(c)+1;
                System.out.printf("[i] Resultado de validación del contacto nº(%d) con nombre: %s --> %s\n",numContacto,c.getNombre(), value);
                switch (value){
                    case NUMERO_CONTACTO_NO_VALIDO : throw new RegistroInvalidoException("\t[!] El contacto nº(" + numContacto + ") con nombre " + c.getNombre() + " tiene datos inválidos en el número: " + c.getTelefono());
                    case EMAIL_NO_VALIDO : throw new RegistroInvalidoException("\t[!] El contacto nº(" + numContacto + ") con nombre " + c.getNombre() + " tiene un email no válido : " + c.getEmail());
                    case NO_ES_ADULTO : throw new RegistroInvalidoException("\t[!] El contacto nº(" + numContacto + ") con nombre " + c.getNombre() + " tiene inválida la fecha: " + c.getNacimiento());
                }
            } catch(RegistroInvalidoException e){
                System.out.println(e.getMessage());
            }
        });
    }





}
