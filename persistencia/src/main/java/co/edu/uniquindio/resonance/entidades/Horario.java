package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Column(name = "descripcion_horario", length = 50 , nullable = false)
    private String descripcion;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicio", nullable = false)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_final", nullable = false)
    private Date horaCierre;

    @Column(name = "continuidad", nullable = false)
    private boolean continuidad;


    public Horario() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public boolean isContinuidad() {
        return continuidad;
    }

    public void setContinuidad(boolean continuidad) {
        this.continuidad = continuidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return codigo == horario.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
