package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

@Entity
public class Calificacion {
    @Id
    @Column(name="codigo",nullable = false)
    private int codigo;

    @Column(name="valor")
    private double valor;

    @Column(name="titulo")
    private String titulo;

    @Column(name="mensaje")
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    public Calificacion() {
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
