package UD1.ej1_1.clientes;

import java.security.SecureRandom;

public class Cliente {
    protected String nombre;
    protected int codigo;
    protected String direccion;
    protected String numeroMovil;
    protected String email;

    public Cliente() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
            this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        SecureRandom sr = new SecureRandom();
        String haciendoCodigo = "" + codigo + sr.nextInt(1000000);
        this.codigo = Integer.valueOf(haciendoCodigo);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroMovil() {
        return numeroMovil;
    }

    public void setNumeroMovil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Cliente: " + this.nombre + ", Código seguro ---[" + this.codigo + "]";
    }
}
