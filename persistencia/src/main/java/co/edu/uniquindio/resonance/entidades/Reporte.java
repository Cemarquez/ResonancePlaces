package co.edu.uniquindio.resonance.entidades;

import lombok.*;
import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Reporte
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Reporte implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="codigo" ,nullable = false )
    @EqualsAndHashCode.Include
    private int codigo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "descripcion", nullable = false )
    private String descripcion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "pdfURL", nullable = false, unique = true)
    private String pdfURL;

    public Reporte(int codigo, Date fecha, String descripcion, String nombre) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }


}
