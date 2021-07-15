package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


/**
 * Clase que corresponde a la entidad Telefono
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Telefono {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo",nullable = false)
    @EqualsAndHashCode.Include
    @NotBlank
    private int codigo;

    @Column(name="numero", length = 14, nullable = false)
    @NotBlank
    private String numero;

    /**
     * Relación que corresponde al lugar al cual pertenece el telefono
     */
    @ManyToOne
    @JoinColumn(name="codigo_lugar",nullable = false)
    @NotBlank
    private Lugar lugar;
}
