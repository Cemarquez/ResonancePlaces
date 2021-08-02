package co.edu.uniquindio.resonance.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase Usuario para la entidad Usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Usuario {

    @Id
    @Column(name="nickname",nullable = false, length = 25)
    @EqualsAndHashCode.Include
    private String nickname;

    @Column(name="nombre",nullable = false,length = 70)
    private String nombre;

    @Email
    @Column(name="email", nullable = false)
    private  String email;

    @Column(name="contrasena",nullable = false)
    private String contrasena;


    /**
     * Relacion entre usuario y favoritos, un usuario tiene muchos favoritos
     */

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Favorito> favoritos ;


    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Reserva> reservas;

    /**
     * Relacion entre usuario y calificaciones, un usuario tiene muchas calificaciones
     */
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Calificacion> calificaciones  ;

    /**
     * Relacion entre usuario y lugares, un usuario tiene muchos lugares
     */
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    @JsonIgnore
    private  List<Lugar> lugares ;

    /**
     * Relacion entre usuario y ciudad, muchas ciudades tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;


    /**
     * Constructor para Usuario
     * @param nickname nickname del usuario
     * @param nombre nombre del usuario
     * @param email email del usuario
     * @param contrasena contrasena del usuario
     */
    public Usuario(String nickname, String nombre, String email, String contrasena) {
        favoritos = new ArrayList<>();
        calificaciones = new ArrayList<>();
        lugares = new ArrayList<>();
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }


}
