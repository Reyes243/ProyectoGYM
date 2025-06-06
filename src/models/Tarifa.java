package models;

public class Tarifa{
    
    private String nombreTarifa;
    private String descripcion;
    private int precio;
    
    public Tarifa(String nombreTarifa, String descripcion, int precio) {
        this.nombreTarifa = nombreTarifa;
        this.descripcion = descripcion;
        this.precio = precio;
    }


    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
