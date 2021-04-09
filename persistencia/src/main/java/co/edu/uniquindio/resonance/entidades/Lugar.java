package co.edu.uniquindio.resonance.entidades;

import org.hibernate.type.descriptor.java.StringTypeDescriptor;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @JoinColumn(name = "codigo_horario", nullable = false)
    private int codigoHorario;

    @JoinColumn(name = "codigo_categoria", nullable = false)
    private int codigoCategoria;

    @JoinColumn(name = "codigo_ubicacion", nullable = false)
    private int codigoUbicacion;

    @JoinColumn(name = "codigo_ciudad", nullable = false)
    private int codigoCiudad;

    @JoinColumn(name = "nickname_moderador", nullable = false)
    private String nicknameModerador;

    @JoinColumn(name = "nickname_usuario", nullable = false)
    private String nicknameUsuario;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    public Lugar(){

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(int codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public int getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(int codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public int getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(int codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNicknameModerador() {
        return nicknameModerador;
    }

    public void setNicknameModerador(String nicknameModerador) {
        this.nicknameModerador = nicknameModerador;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

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
