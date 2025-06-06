package models;

import java.util.List;

public class Clase {
    private int idClase;
    private int idUsuario;
    private String nombreClase;
    private List<ClaseHorario> horarios;

    public Clase() {
    }

    public Clase(int idClase, int idUsuario, String nombreClase) {
        this.idClase = idClase;
        this.idUsuario = idUsuario;
        this.nombreClase = nombreClase;
    }

    // Getters y Setters
    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public List<ClaseHorario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<ClaseHorario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "idClase=" + idClase +
                ", idUsuario=" + idUsuario +
                ", nombreClase='" + nombreClase + '\'' +
                ", horarios=" + horarios +
                '}';
    }
}