package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="codigo" ,nullable = false )
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name="codigo_lugar")
    private Lugar lugar;

    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;


    public Reserva(LocalDate fechaInicio, LocalDate fechaFin, Lugar lugar, Usuario usuario) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.lugar = lugar;
        this.usuario = usuario;
    }
}
