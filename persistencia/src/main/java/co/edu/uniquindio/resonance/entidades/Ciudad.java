package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Ciudad
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    @Column(name = "codigo", nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;

    @NotBlank
    @Column(name = "nombre_ciudad", length = 50 , nullable = false)
    private String nombre;

    /**
     * Relación que corresponde a la lista de lugares que pertenecen a la ciudad n ---> 1
     */
    @OneToMany(mappedBy = "ciudad")
    @ToString.Exclude
    private List<Lugar> lugares;

    public Ciudad(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
