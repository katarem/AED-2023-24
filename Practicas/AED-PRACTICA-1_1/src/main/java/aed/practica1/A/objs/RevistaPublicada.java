package aed.practica1.A.objs;

public class RevistaPublicada extends ArticulosPublicados{
    public String titulo;
    public String formato;
    public String correo;
    public String nombreAutor;
    public String editorial;

    public RevistaPublicada() {
    }

    public RevistaPublicada(String titulo, String formato, String correo, String nombreAutor, String editorial, int numeroPaginas, float precio) {
        this.titulo = titulo;
        this.formato = formato;
        this.correo = correo;
        this.nombreAutor = nombreAutor;
        this.editorial = editorial;
        this.numeroDePaginas = numeroPaginas;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("""
                Titulo: %s
                Autor: %s
                Correo: %s
                Editorial: %s
                Páginas: %d
                Precio: %.2f€
                ###################################
                """,titulo,nombreAutor,correo,editorial,numeroDePaginas,precio);
    }
}
