package UD1.ej1_2.pagables;

import java.security.SecureRandom;
import java.util.IllegalFormatException;

public class Factura implements Pagable{
    private String codigo;
    private String descripcion;
    private int cantidad;
    private double precioPorProducto;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        SecureRandom sr = new SecureRandom();
        this.codigo = codigo + sr.nextInt(1000000);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioPorProducto() {
        return precioPorProducto;
    }

    public void setPrecioPorProducto(double precioPorProducto) {
        this.precioPorProducto = precioPorProducto;
    }

    @Override
    public double obtenerImportePagable() {
        return cantidad * precioPorProducto;
    }

    @Override
    public String toString(){
        return String.format("[i] Factura: \n--> Código: %s (%s)\n--> Cantidad: %d\n--> Precio por producto: %.2f€ (Eur)", codigo, descripcion, cantidad, precioPorProducto);
    }

}
