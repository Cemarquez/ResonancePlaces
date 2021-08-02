package co.edu.uniquindio.resonance.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "codigo")
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(name = "nombre_categoria", length = 50 , nullable = false)
    private String nombre;

    @Column(name = "descripcion_categoria", nullable = false)
    private String descripcion;

    /**
     * Entidad que corresponde a la lista de lugares que están ligados a la misma categoria
     */
    @OneToMany(mappedBy = "categoria")
    @ToString.Exclude
    @JsonIgnore
    private List<Lugar> lugar;

    public Categoria(@NotBlank String nombre, @NotBlank String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
