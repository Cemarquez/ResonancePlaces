package co.edu.uniquindio.resonance.entidades;

import org.dom4j.rule.Mode;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @ManyToOne
    @JoinColumn(name="codigo_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="codigo_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name="nickname_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "lugar")
    private List<Telefono> telefono;

    @OneToMany(mappedBy = "lugar")
    private List<Foto> foto;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @OneToMany(mappedBy = "lugar")
    private List<Horario> horarios;

    @OneToMany(mappedBy = "lugar")
    private List<Calificacion> calificaciones;

    @ManyToOne
    @JoinColumn(name = "nickname_moderador", nullable = false)
    private Moderador moderador;

    @ManyToOne
    @JoinColumn(name = "codigo_ciudad")
    private Ciudad ciudad;



    public Lugar(){

    }



    /*
        Inicio de getters and setters
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Telefono> getTelefono() {
        return telefono;
    }

    public void setTelefono(List<Telefono> telefono) {
        this.telefono = telefono;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    public boolean isEstado() {
        return estado;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*
        Fin de getters and setters
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lugar lugar = (Lugar) o;
        return codigo == lugar.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
