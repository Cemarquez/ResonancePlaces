package co.edu.uniquindio.resonance.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.dom4j.rule.Mode;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    @Column(name = "codigo", nullable = false)
    @EqualsAndHashCode.Include
    private int codigo;

    /**
     * Relacion que corresponde a la categoria en la que pertenece el lugar
     */

    @ManyToOne
    @JoinColumn(name="codigo_categoria", nullable = false)

    private Categoria categoria;

    /**
     * Relacion que corresponde a la ubicacion geografica del lugar
     */

    /**
     * Relación que corresponde al usuario que registró el lugar
     */
    @ManyToOne
    @JoinColumn(name="nickname_usuario")

    private Usuario usuario;


    /**
     * Relacion que corresponde a la lista de fotos que fueron asignadas a un lugar
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Column(name="url_foto")
    @JsonIgnore
    private List<String> foto;

    @Column(name ="latitud")
    private double latitud;

    @Column(name ="longitud")
    private double longitud;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", columnDefinition = "boolean default false")
    private boolean estado;

    @Column(name = "rechazado")
    private boolean rechazado;
    /**
     * Relacion que corresponde a la lista de horarios asignados a un lugar
     */
    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    @JsonIgnore
    private List<Horario> horarios;

    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    @JsonIgnore
    private List<Reserva> reservas;

    /**
     * Relacion que corresponde a la lista de calificaciones/comentarios aportados por los visitantes de un lugar
     */
    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    @JsonIgnore
    private List<Calificacion> calificaciones;


    /**
     * Relacion que corresponde al moderador que autorizó el lugar
     */
    @ManyToOne
    @JoinColumn(name = "nickname_moderador")
    @JsonIgnore
    private Moderador moderador;

    /**
     * Relacion que corresponde a la ciudad en la que pertenece el lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;


    public Lugar(Categoria categoria ,String descripcion, String nombre) {
        foto = new ArrayList<>();
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    @Transactional
    @JsonIgnore
    public String getImagenPrincipal(){
        if(getFoto().size()>0){
            return foto.get(0);
        }else{
            return "vacio.png";
        }
    }
}
