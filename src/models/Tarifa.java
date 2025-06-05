package models;

public class Tarifa{
    private int idTarifa;
    private String nombreTarifa;
    private String descripcion;
    private int precio;

    public Tarifa() {
    }

    public Tarifa(int idTarifa, String nombreTarifa, String descripcion, int precio) {
        this.idTarifa = idTarifa;
        this.nombreTarifa = nombreTarifa;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    
    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
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
