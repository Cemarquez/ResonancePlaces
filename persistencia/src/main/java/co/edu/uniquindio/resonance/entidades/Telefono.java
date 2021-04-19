package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo",nullable = false)
    private int codigo;
    @Column(name="numero", length = 14)
    private String numero;

    @ManyToOne
    @JoinColumn(name="codigo_lugar",nullable = false)
    private Lugar lugar;


    public Telefono(){

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
     * Método equals sobreescrito para la clase Telefono
     * @param o Objeto a comparar
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefono telefono = (Telefono) o;
        return codigo == telefono.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
