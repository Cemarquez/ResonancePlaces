package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

@Entity
public class Administrador {

    @Id
    @Column(name="nickname", nullable = false)
    private String nickname;
    @Column(name="nombre")
    private String nombre;
    @Column(name="email")
    private String email;
    @Column(name="contrasena", nullable = false)
    private String contrasena;


    public Administrador() {
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

        Administrador that = (Administrador) o;

        return nickname != null ? nickname.equals(that.nickname) : that.nickname == null;
    }

    @Override
    public int hashCode() {
        return nickname != null ? nickname.hashCode() : 0;
    }
}
