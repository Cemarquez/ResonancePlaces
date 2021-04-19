package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Clase que corresponde a la entidad Categoria
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "nombre_categoria", length = 50 , nullable = false)
    private String nombre;

    @Column(name = "descripcion_categoria", nullable = false)
    private String descripcion;

    /**
     * Entidad que corresponde a la lista de lugares que están ligados a la misma categoria
     */
    @OneToMany(mappedBy = "categoria")
    private List<Lugar> lugar;



    public Categoria() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Lugar> getLugar() {
        return lugar;
    }

    public void setLugar(List<Lugar> lugar) {
        this.lugar = lugar;
    }

    /*
        Fin de getters and setters
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return codigo == categoria.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
