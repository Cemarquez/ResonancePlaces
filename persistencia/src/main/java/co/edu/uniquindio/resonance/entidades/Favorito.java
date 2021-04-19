package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

@Entity
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Favorito(){

    }

    public Favorito(Usuario usuario, Lugar lugar) {

        this.usuario = usuario;
        this.lugar = lugar;
    }

    /*
        Inicio de getters and setters
     */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /*
        Fin de getters and setters
     */

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
