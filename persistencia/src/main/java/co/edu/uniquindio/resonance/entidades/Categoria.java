package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "nombre_categoria", length = 50 , nullable = false)
    private String nombre;

    @Column(name = "descripcion_categoria", length = 50 , nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Lugar> lugar;



    public Categoria() {
        super();
    }

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
