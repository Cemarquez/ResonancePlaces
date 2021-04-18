package co.edu.uniquindio.resonance.entidades;

import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reporte implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="codigo" ,nullable = false )
    private int codigo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "descripcion", nullable = false )
    private String descripcion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "pdfURL", nullable = false, unique = true)
    private String pdfURL;


    public Reporte() {
    }

    public Reporte(int codigo, Date fecha, String descripcion, String nombre) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getPdfURL() {
        return pdfURL;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reporte reporte = (Reporte) o;
        return codigo == reporte.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
