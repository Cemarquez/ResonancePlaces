package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo",nullable = false)
    private int codigo;
    @Column(name="urlFoto")
    private String urlFoto;
    @ManyToOne
    @JoinColumn(name="codigo_lugar")
    private Lugar lugar;

    public Foto(int codigo, Lugar lugar) {
        this.codigo = codigo;
        this.lugar = lugar;
    }

    public Foto() {
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
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

        Foto foto = (Foto) o;

        return codigo == foto.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
