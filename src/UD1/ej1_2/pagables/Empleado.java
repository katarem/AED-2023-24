package UD1.ej1_2.pagables;

public class Empleado implements Pagable{

    private String nombre;
    private String apellido;
    private String seguridadSocial;

    @Override
    public double obtenerImportePagable() {
        return 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSeguridadSocial() {
        return seguridadSocial;
    }

    public void setSeguridadSocial(String seguridadSocial) {
        this.seguridadSocial = seguridadSocial;
    }

    @Override
    public String toString(){
        return String.format("[i] Empleado de servicios: %s %s\n--> Número de la seguridad social: %s",nombre,apellido,seguridadSocial);
    }
}
