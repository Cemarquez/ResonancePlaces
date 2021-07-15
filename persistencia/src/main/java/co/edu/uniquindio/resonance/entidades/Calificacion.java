package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name="valor", nullable = false)
    @NotBlank
    private double valor;

    @Column(name="titulo", nullable = false)
    @NotBlank
    private String titulo;

    @Column(name="mensaje", nullable = false)
    @NotBlank
    private String mensaje;

    /**
     * Relacion entre calificacion y usuario, muchas calificaciones tienen un usuario
     */
    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Relacion entre calificacion y lugar, muchas calificaciones tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;


    /**
     * Constructor de Calificacion
     * @param valor Valor de la calificacion
     * @param titulo Titulo de la calificacion
     * @param mensaje Mensaje de la calificacion
     * @param usuario Usuario que hace la calificacion
     * @param lugar Lugar que es calificado
     */
    public Calificacion( double valor, String titulo, String mensaje, Usuario usuario, Lugar lugar) {

        this.valor = valor;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.lugar = lugar;
    }


}
