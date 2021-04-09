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
    private int codigoLugar;
    @JoinColumn(name ="nickname_usuario", nullable = false)
    private String nicknameUsuario;
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

    public int getCodigoLugar() {
        return codigoLugar;
    }

    public void setCodigoLugar(int codigoLugar) {
        this.codigoLugar = codigoLugar;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
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
