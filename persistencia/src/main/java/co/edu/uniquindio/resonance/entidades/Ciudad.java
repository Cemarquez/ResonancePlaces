package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "nombre_ciudad", length = 50 , nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Lugar> lugares;

    public Ciudad() {
    }

    public Ciudad(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /*
        Fin de getters and setters
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return codigo == ciudad.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
