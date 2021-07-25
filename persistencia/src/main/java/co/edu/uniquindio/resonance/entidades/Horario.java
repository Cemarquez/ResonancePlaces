package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
    @Column(name = "codigo", length = 50 , nullable = false)
    private int codigo;

    @Column(name = "dia_horario", length = 50 , nullable = false)
    private String dia;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_final", nullable = false)
    private LocalTime horaCierre;

    private boolean cerrado;

    /**
     * Relacion entre horario y lugar, muchos horarios tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;


    public Horario(String dia, boolean cerrado) {
        this.dia = dia;
        this.cerrado = cerrado;
    }

    public Horario(String dia, LocalTime horaInicio, LocalTime horaCierre, boolean cerrado, Lugar lugar) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaCierre = horaCierre;
        this.cerrado = cerrado;
        this.lugar = lugar;
    }

    public boolean updateCerrado(){
        return !cerrado;
    }
}
