package aed.practica1.A.utils;

import aed.practica1.A.exceptions.CampoVacioException;
import aed.practica1.A.objs.ArticulosPublicados;
import aed.practica1.A.objs.BoletinPublicado;
import aed.practica1.A.objs.RevistaPublicada;
import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validador {

    public static final StringBuilder pilaErrores = new StringBuilder();

    private static boolean huboErrores;

    public static void comprobarMail(String mail){
        try{
            //primero si está vacío
            if(mail.isEmpty() || mail.isBlank()) throw new CampoVacioException("Falta el mail.");
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            Matcher matcher = pattern.matcher(mail);
            if(!matcher.matches()) throw new PatternSyntaxException("Formato de correo no válido. Solo punto (.), guión (-) y guión bajo(_).",mail,-1);
        } catch(CampoVacioException e){
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
        } catch(PatternSyntaxException e){
            pilaErrores.append(e.getDescription()).append("\n");
            huboErrores = true;
        }
    }
    public static void comprobarNumeroPaginas(String numero){
        try{
            if(numero.isEmpty() || numero.isBlank()) throw new CampoVacioException("Falta el número de páginas");
            var num = Integer.parseInt(numero);
            if(num < 0) throw new NumberFormatException();
        } catch(CampoVacioException | NumberFormatException e){
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
        }

    }
    public static void comprobarFormato(String formato){
        try{
            if(formato.equals("Formato")) throw new CampoVacioException("Formato no elegido");
        } catch(CampoVacioException e){
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
        }
    }

    public static void comprobarFecha(String fecha){
        try{
            if(fecha.isEmpty() || fecha.isBlank()) throw new CampoVacioException("Falta la fecha");
            var checker = new SimpleDateFormat("dd/MM/YYYY");
            checker.setLenient(false);
            Date dateCheck = checker.parse(fecha);
        } catch(CampoVacioException | ParseException e){
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
        } catch(PatternSyntaxException e){
            pilaErrores.append(e.getDescription()).append("\n");
            huboErrores = true;
        }
    }

    private static void comprobarPrecio(String contenido){
        try {
            if (contenido.isEmpty() || contenido.isBlank()) throw new CampoVacioException("Falta el precio.");
            else if(contenido.contains(",")) throw new NumberFormatException("El precio se escribe con . no con ,");
            var precio = Float.parseFloat(contenido);
        } catch(CampoVacioException | NumberFormatException e){
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
        }
    }

    public static ArticulosPublicados comprobarDatos(List<String> datosCampos, boolean isRevista){
       if(isRevista){
           //extraemos por campos
           var titulo = datosCampos.get(0);
           var formato = datosCampos.get(1);
           var mail = datosCampos.get(2);
           var autor = datosCampos.get(3);
           var editorial = datosCampos.get(4);
           var numeroPaginas = datosCampos.get(5);
           var precio = datosCampos.get(6);

           //primero comprobaremos que los campos no estén vacíos y luego comprobamos si cumple las condiciones si no está vacío
           comprobarMail(mail);
           comprobarNumeroPaginas(numeroPaginas);
           comprobarPrecio(precio);
           comprobarFormato(formato);

           if(huboErrores) return null;
           else return new RevistaPublicada(titulo,formato,mail,autor,editorial,Integer.parseInt(numeroPaginas),Float.parseFloat(precio));
       } else{
           var nombre = datosCampos.get(0);
           var fecha = datosCampos.get(1);
           var numeroPaginas = datosCampos.get(2);
           var precio = datosCampos.get(3);

           comprobarFecha(fecha);
           comprobarNumeroPaginas(numeroPaginas);
           comprobarPrecio(precio);

           if(nombre.isBlank() || nombre.isEmpty()){
               pilaErrores.append("Falta el nombre.").append("\n");
               huboErrores = true;
           }
           if(huboErrores) return null;
           else return new BoletinPublicado(nombre,fecha,Integer.parseInt(numeroPaginas),Float.parseFloat(precio));
       }
    }
}
