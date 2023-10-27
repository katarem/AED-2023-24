package aed.practica1.A.objs;

public class BoletinPublicado extends ArticulosPublicados{

    private String nombre;
    private String fecha;

    public BoletinPublicado() {
    }

    public BoletinPublicado(String nombre, String fecha, int numeroPaginas,float precio) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.numeroDePaginas = numeroPaginas;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "BoletinPublicado{" +
                "nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", numeroDePaginas=" + numeroDePaginas +
                ", precio=" + precio +
                '}';
    }
}
