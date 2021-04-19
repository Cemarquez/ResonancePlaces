package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Ubicacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "longitud", nullable = false)
    private double longitud;


    public Ubicacion() {
        super();
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /*
        Fin de getters and setters
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return codigo == ubicacion.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
