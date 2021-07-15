package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;
/**
 * Clase Horario para la entidad Horario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @NotBlank
    @Column(name = "codigo", length = 50 , nullable = false)
    private int codigo;

    @Column(name = "descripcion_horario", length = 50 , nullable = false)
    private String descripcion;

    @Temporal(TemporalType.TIME)
    @NotBlank
    @Column(name = "hora_inicio", nullable = false)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    @NotBlank
    @Column(name = "hora_final", nullable = false)
    private Date horaCierre;

    @NotBlank
    @Column(name = "continuidad", nullable = false)
    private boolean continuidad;

    /**
     * Relacion entre horario y lugar, muchos horarios tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;

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
}
