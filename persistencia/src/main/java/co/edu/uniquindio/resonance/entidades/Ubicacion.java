package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Ubicacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    @EqualsAndHashCode.Include
    @NotBlank
    private int codigo;

    @Column(name = "latitud", nullable = false)
    @NotBlank
    private double latitud;

    @Column(name = "longitud", nullable = false)
    @NotBlank
    private double longitud;

}
