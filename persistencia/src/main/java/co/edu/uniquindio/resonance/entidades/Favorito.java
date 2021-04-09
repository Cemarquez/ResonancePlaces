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
    private String nicknameUsuario;
    @JoinColumn(name = "codigo_lugar",nullable = false)
    private int codigoLugar;

    public Favorito(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public int getCodigoLugar() {
        return codigoLugar;
    }

    public void setCodigoLugar(int codigoLugar) {
        this.codigoLugar = codigoLugar;
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
