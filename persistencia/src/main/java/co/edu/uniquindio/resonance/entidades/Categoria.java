package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Categoria
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    @Column(name = "codigo", nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;

    @NotBlank
    @Column(name = "nombre_categoria", length = 50 , nullable = false)
    private String nombre;

    @NotBlank
    @Column(name = "descripcion_categoria", nullable = false)
    private String descripcion;

    /**
     * Entidad que corresponde a la lista de lugares que están ligados a la misma categoria
     */
    @OneToMany(mappedBy = "categoria")
    @ToString.Exclude
    private List<Lugar> lugar;
}
