package co.edu.uniquindio.resonance.entidades;

import org.hibernate.type.descriptor.java.StringTypeDescriptor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @ManyToOne
    @JoinColumn(name="codigo_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="codigo_ubicacion", nullable = false)
    private Ubicacion ubicacion;

    @OneToMany(mappedBy = "lugar")
    private List<Telefono> telefono;

    @OneToMany(mappedBy = "lugar")
    private List<Foto> foto;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private boolean estado;


    public Lugar(){

    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lugar lugar = (Lugar) o;
        return codigo == lugar.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
