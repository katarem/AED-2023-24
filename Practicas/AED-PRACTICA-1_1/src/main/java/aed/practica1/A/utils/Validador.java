package aed.practica1.A.utils;

import aed.practica1.A.exceptions.CampoVacioException;
import aed.practica1.A.objs.ArticulosPublicados;
import aed.practica1.A.objs.BoletinPublicado;
import aed.practica1.A.objs.RevistaPublicada;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validador {

    private static final StringBuilder pilaErrores = new StringBuilder();

    private static boolean huboErrores;

    public static boolean comprobarMail(String mail){
        try{
            //primero si está vacío
            if(mail.isEmpty() || mail.isBlank()) throw new CampoVacioException("Falta el mail.");

            Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$\n");
            Matcher matcher = pattern.matcher(mail);
            if(!matcher.matches()) throw new PatternSyntaxException("Formato de correo no válido. Solo punto (.), guión (-) y guión bajo(_).",mail,-1);
            return true;
        } catch(CampoVacioException e){
            pilaErrores.append(e.getMessage()).append("\n");
            return false;
        } catch(PatternSyntaxException e){
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
            return false;
        }
    }

    public static boolean comprobarNumeroPaginas(String numero){
        try{
            if(numero.isEmpty() || numero.isBlank()) throw new CampoVacioException("Falta el número de páginas");
            var num = Integer.parseInt(numero);
            if(num < 0) throw new NumberFormatException();
            return true;
        } catch(CampoVacioException e){
            pilaErrores.append(e.getMessage()).append("\n");
            return false;
        } catch(NumberFormatException e){
            e = new NumberFormatException("Número de páginas inválido");
            pilaErrores.append(e.getMessage()).append("\n");
            huboErrores = true;
            return false;
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

    public static boolean comprobarFecha(String fecha){
        try{
            if(fecha.isEmpty() || fecha.isBlank()) throw new CampoVacioException("Falta la fecha");
            Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}"); // 01/01/2001
            Matcher matcher = pattern.matcher(fecha);
            if(!matcher.matches()) throw new PatternSyntaxException("La fecha introducida debe estar con formato \"dd/MM/yyyy\"",fecha,-1);
            var checkFecha = fecha.split("/");
            var dias = Integer.parseInt(checkFecha[0]);
            var meses = Integer.parseInt(checkFecha[1]);
            var anios = Integer.parseInt(checkFecha[2]);
            if((dias > 31 || dias < 0) || (meses > 12 || meses < 0) || anios < 0) throw new PatternSyntaxException("La fecha introducida debe estar con formato \"dd/MM/yyyy\"",fecha,-1);
            return true;
        } catch(CampoVacioException e){
            pilaErrores.append(e.getMessage()).append("\n");
            return false;
        } catch(PatternSyntaxException e){
            pilaErrores.append(e.getDescription()).append("\n");
            huboErrores = true;
            return false;
        }
    }

    private static boolean comprobarPrecio(String contenido){
        try {
            if (contenido.isEmpty() || contenido.isBlank()) throw new CampoVacioException("Falta el precio.");

            var precio = Double.parseDouble(contenido);



            return true;
        } catch(CampoVacioException e){
            pilaErrores.append(e.getMessage()).append("\n");
            return false;
        }
    }

    public static ArticulosPublicados comprobarDatos(List<String> datosCampos, boolean isRevista){

       if(isRevista){
           //extraemos por campos
           var titulo = datosCampos.get(0);
           var autor = datosCampos.get(1);
           var editorial = datosCampos.get(2);
           var mail = datosCampos.get(2);
           var numeroPaginas = datosCampos.get(1);
           var precio = datosCampos.get(2);
           var formato = datosCampos.get(3);

           //primero comprobaremos que los campos no estén vacíos y luego comprobamos si cumple las condiciones si no está vacío
           comprobarMail(mail);
           comprobarNumeroPaginas(numeroPaginas);
           comprobarPrecio(precio);
           comprobarFormato(formato);

           if(huboErrores) Alertas.error(pilaErrores.toString());
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
           if(huboErrores) Alertas.error(pilaErrores.toString());
           else return new BoletinPublicado(nombre,fecha,Integer.parseInt(numeroPaginas),Float.parseFloat(precio));
       }
        return null;
    }
}
