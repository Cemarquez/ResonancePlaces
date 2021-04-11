package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.Objects;

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


    @ManyToOne
    @JoinColumn(name = "nickname_administrador", nullable = false)
    private Administrador administrador;


    public Moderador() {

    }

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
