package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Clase Favorito para la entidad favorito
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    @Column(name = "codigo", nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;


    /**
     * Relacion entre favorito y usuario, muchos favoritos tienen un usuario
     */
    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Relacion entre favorito y lugar, muchos favoritos tienen un lugar
     */
    @ManyToOne
    @NotBlank
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;

    /**
     * Constructor para Favorito
     * @param usuario Usuario que agrega el favorito
     * @param lugar Lugar lugar agregado a favorito
     */
    public Favorito(Usuario usuario, Lugar lugar) {

        this.usuario = usuario;
        this.lugar = lugar;
    }

}
