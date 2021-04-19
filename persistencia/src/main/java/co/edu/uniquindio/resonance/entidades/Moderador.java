package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
/**
 * Clase que corresponde a la entidad Moderador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
public class Moderador {

    @Id
    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name="nombre", length = 70)
    private String nombre;

    @Column(name="email")
    private String email;

    @Column(name="contrasena", nullable = false)
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
    private List<Lugar> lugares;

    public Moderador() {
    }

    public Moderador(String nickname, String nombre, String email, String contrasena) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    /*
        Inicio de getters and setters
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    /*
        Fin de getters and setters
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moderador moderador = (Moderador) o;
        return Objects.equals(nickname, moderador.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
