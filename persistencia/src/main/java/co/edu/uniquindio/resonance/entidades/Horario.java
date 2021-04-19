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

    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;


    public Horario() {
        super();
    }

    public Horario(String descripcion, Date horaInicio, Date horaCierre, boolean continuidad, Lugar lugar) {

        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaCierre = horaCierre;
        this.continuidad = continuidad;
        this.lugar = lugar;
    }

    /*
        Inicio de getters and setters
     */
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

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /*
        Fin de getters and setters
     */

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
