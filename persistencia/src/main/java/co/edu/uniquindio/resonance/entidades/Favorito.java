package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

/**
 * Clase Favorito para la entidad favorito
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */

@Entity
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;


    /**
     * Relacion entre favorito y usuario, muchos favoritos tienen un usuario
     */
    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Relacion entre favorito y lugar, muchos favoritos tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;


    /*
        Inicio de getters and setters
     */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Favorito(){

    }

    /**
     * Constructor para Favorito
     * @param usuario Usuario que agrega el favorito
     * @param lugar Lugar lugar agregado a favorito
     */
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


    /**
     * Método equals sobreescrito para la clase Favorito
     * @param o Objeto a comparar
     * @return
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
