package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
/**
 * Clase que corresponde a la entidad Moderador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Moderador {

    @Id
    @Column(name="nickname", nullable = false)
    @EqualsAndHashCode.Include
    @NotBlank
    private String nickname;

    @Column(name="nombre", length = 70)
    private String nombre;

    @Email
    @Column(name="email")
    private String email;

    @Column(name="contrasena", nullable = false)
    @NotBlank
    private String contrasena;


    /**
     * Relación correspondiente al administrador que le otorgó el cargo de moderador a este usuario n ---> 1
     */
    @ManyToOne
    @JoinColumn(name = "nickname_administrador", nullable = false)
    private Administrador administrador;

    /**
     * Relación correspondiente a la lista de lugares que el moderador ha autorizado 1 ---> n
     */
    @OneToMany( mappedBy = "moderador")
    @ToString.Exclude
    private List<Lugar> lugares;

    public Moderador(String nickname, String nombre, String email, String contrasena) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }
}
