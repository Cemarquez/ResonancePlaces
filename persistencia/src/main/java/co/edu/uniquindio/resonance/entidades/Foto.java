package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Clase que corresponde a la entidad Foto
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    @Column(name="codigo",nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name="urlFoto")
    private String urlFoto;

    /**
     * Relación correspondiente al lugar al cual pertenece la foto n ---> 1
     */
    @ManyToOne
    @JoinColumn(name="codigo_lugar")
    private Lugar lugar;

    public Foto(int codigo, Lugar lugar) {
        this.codigo = codigo;
        this.lugar = lugar;
    }
}
