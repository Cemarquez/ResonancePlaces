package co.edu.uniquindio.resonance.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Favorito {

    @Id
    @Column(name = "codigo", nullable = false)
    private int codigo;
    @JoinColumn(name ="nickname_usuario", nullable = false)
    private String nickname_usuario;
    @JoinColumn(name = "codigo_lugar",nullable = false)
    private int codigo_lugar;

    public Favorito(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNickname_usuario() {
        return nickname_usuario;
    }

    public void setNickname_usuario(String nickname_usuario) {
        this.nickname_usuario = nickname_usuario;
    }

    public int getCodigo_lugar() {
        return codigo_lugar;
    }

    public void setCodigo_lugar(int codigo_lugar) {
        this.codigo_lugar = codigo_lugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorito favorito = (Favorito) o;

        return codigo == favorito.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
