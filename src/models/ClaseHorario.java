package models;

public class ClaseHorario {
    private int idClaseHorario;
    private int idClase;
    private DiaSemana diaSemana;
    private Turno turno;

    public enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }

    public enum Turno {
        MATUTINO, VESPERTINO, MIXTO
    }

    public ClaseHorario() {
    }

    public ClaseHorario(int idClaseHorario, int idClase, DiaSemana diaSemana, Turno turno) {
        this.idClaseHorario = idClaseHorario;
        this.idClase = idClase;
        this.diaSemana = diaSemana;
        this.turno = turno;
    }

    // Getters y Setters
    public int getIdClaseHorario() {
        return idClaseHorario;
    }

    public void setIdClaseHorario(int idClaseHorario) {
        this.idClaseHorario = idClaseHorario;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "ClaseHorario{" +
                "idClaseHorario=" + idClaseHorario +
                ", idClase=" + idClase +
                ", diaSemana=" + diaSemana +
                ", turno=" + turno +
                '}';
    }
}
