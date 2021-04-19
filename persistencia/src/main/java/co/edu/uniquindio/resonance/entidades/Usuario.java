package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase Usuario para la entidad Usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
public class Usuario {

    @Id
    @Column(name="nickname",nullable = false, length = 25)
    private String nickname;

    @Column(name="nombre",nullable = false,length = 70)
    private String nombre;

    @Column(name="email", nullable = false)
    private  String email;

    @Column(name="contrasena",nullable = false)
    private String contrasena;

    /**
     * Relacion entre usuario y favoritos, un usuario tiene muchos favoritos
     */
    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos ;

    /**
     * Relacion entre usuario y calificaciones, un usuario tiene muchas calificaciones
     */
    @OneToMany(mappedBy = "usuario")
    private List<Calificacion> calificaciones  ;

    /**
     * Relacion entre usuario y lugares, un usuario tiene muchos lugares
     */
    @OneToMany(mappedBy = "usuario")
    private  List<Lugar> lugares ;

    /**
     * Relacion entre usuario y ciudad, muchas ciudades tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;

    public Usuario() {

    }

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

    /*
        Inicio de getters and setters
     */
    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    /*
        Fin de getters and setters
     */
    /**
     * Método equals sobreescrito para la clase Usuario
     * @param o Objeto a comparar
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return nickname != null ? nickname.equals(usuario.nickname) : usuario.nickname == null;
    }

    @Override
    public int hashCode() {
        return nickname != null ? nickname.hashCode() : 0;
    }
}
