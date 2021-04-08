package co.edu.uniquindio.resonance.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Calificacion {
    @Id
    @Column(name="codigo",nullable = false)
    private int codigo;
    @JoinColumn(name = "codigo_lugar",nullable = false)
    private int codigo_lugar;
    @JoinColumn(name ="nickname_usuario", nullable = false)
    private String nickname_usuario;
    @Column(name="valor")
    private double valor;
    @Column(name="titulo")
    private String titulo;
    @Column(name="mensaje")
    private String mensaje;

    public Calificacion() {
    }




    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo_lugar() {
        return codigo_lugar;
    }

    public void setCodigo_lugar(int codigo_lugar) {
        this.codigo_lugar = codigo_lugar;
    }

    public String getNickname_usuario() {
        return nickname_usuario;
    }

    public void setNickname_usuario(String nickname_usuario) {
        this.nickname_usuario = nickname_usuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calificacion that = (Calificacion) o;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
