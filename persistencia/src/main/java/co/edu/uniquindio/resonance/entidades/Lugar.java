package co.edu.uniquindio.resonance.entidades;

import lombok.*;
import org.dom4j.rule.Mode;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Lugar
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    @Column(name = "codigo", nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;

    /**
     * Relacion que corresponde a la categoria en la que pertenece el lugar
     */
    @ManyToOne
    @NotBlank
    @JoinColumn(name="codigo_categoria", nullable = false)
    private Categoria categoria;

    /**
     * Relacion que corresponde a la ubicacion geografica del lugar
     */
    @ManyToOne
    @NotBlank
    @JoinColumn(name="codigo_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    /**
     * Relación que corresponde al usuario que registró el lugar
     */
    @ManyToOne
    @NotBlank
    @JoinColumn(name="nickname_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Relación que corresponde a la lista de telefonos registrados en un lugar
     */
    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    private List<Telefono> telefono;

    /**
     * Relacion que corresponde a la lista de fotos que fueron asignadas a un lugar
     */
    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    private List<Foto> foto;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    /**
     * Relacion que corresponde a la lista de horarios asignados a un lugar
     */
    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    private List<Horario> horarios;

    /**
     * Relacion que corresponde a la lista de calificaciones/comentarios aportados por los visitantes de un lugar
     */
    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    private List<Calificacion> calificaciones;

    /**
     * Relacion que corresponde al moderador que autorizó el lugar
     */
    @ManyToOne
    @JoinColumn(name = "nickname_moderador", nullable = false)
    private Moderador moderador;

    /**
     * Relacion que corresponde a la ciudad en la que pertenece el lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;
}
