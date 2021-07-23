package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Clase Calificacion para la entidad calificacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo",nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name="valor", nullable = false)
    private int valor;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Column(name="titulo", nullable = false)
    @NotBlank
    private String titulo;

    @Column(name="mensaje", nullable = false)
    private String mensaje;

    /**
     * Relacion entre calificacion y usuario, muchas calificaciones tienen un usuario
     */
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Relacion entre calificacion y lugar, muchas calificaciones tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;


    public Calificacion(int valor,  String titulo, String mensaje, Usuario usuario, Lugar lugar) {
        this.valor = valor;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.lugar = lugar;
    }
}
