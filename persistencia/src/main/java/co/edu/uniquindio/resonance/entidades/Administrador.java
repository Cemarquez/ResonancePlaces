package co.edu.uniquindio.resonance.entidades;

import lombok.*;
import org.dom4j.rule.Mode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Clase que corresponde a la entidad Administrador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Administrador {

    @Id
    @Column(name="nickname", nullable = false)
    @EqualsAndHashCode.Include
    private String nickname;

    @Column(name="nombre", length = 70)
    @NotBlank
    private String nombre;

    @Column(name="email")
    @NotBlank
    @Email
    private String email;

    @Column(name="contrasena", nullable = false)
    @NotBlank
    private String contrasena;

    /**
     * Relación que corresponde a la lista de moderadores asignados por el administrador
     */
    @OneToMany (mappedBy = "administrador")
    @ToString.Exclude
    private List<Moderador> moderadores;

    /**
     * Relación que corresponde a la lista de reportes generados por el administrador
     */
    @OneToMany
    @ToString.Exclude
    private  List<Reporte> reportes;

    public Administrador(String nickname, String nombre, String email, String contrasena) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
}
