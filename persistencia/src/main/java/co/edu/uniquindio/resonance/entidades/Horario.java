package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
/**
 * Clase Horario para la entidad Horario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */

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

    /**
     * Relacion entre horario y lugar, muchos horarios tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;


    public Horario() {
        super();
    }

    /**
     * Constructor para la clase Horario
     * @param descripcion descripcion del horario
     * @param horaInicio hora inicio del horario
     * @param horaCierre hora cierre del horario
     * @param continuidad continuidad del horario
     * @param lugar lugar del horario
     */
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

    /**
     * Método equals sobreescrito para la clase Horario
     * @param o Objeto a comparar
     * @return
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
