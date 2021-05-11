package co.edu.uniquindio.resonance.entidades;

import javax.persistence.*;

/**
 * Clase Calificacion para la entidad calificacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="codigo",nullable = false)
    private int codigo;

    @Column(name="valor", nullable = false)
    private double valor;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Column(name="mensaje", nullable = false)
    private String mensaje;

    /**
     * Relacion entre calificacion y usuario, muchas calificaciones tienen un usuario
     */
    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    /**
     * Relacion entre calificacion y lugar, muchas calificaciones tienen un lugar
     */
    @ManyToOne
    @JoinColumn(name = "codigo_lugar", nullable = false)
    private Lugar lugar;

    public Calificacion() {
    }

    /**
     * Constructor de Calificacion
     * @param valor Valor de la calificacion
     * @param titulo Titulo de la calificacion
     * @param mensaje Mensaje de la calificacion
     * @param usuario Usuario que hace la calificacion
     * @param lugar Lugar que es calificado
     */
    public Calificacion( double valor, String titulo, String mensaje, Usuario usuario, Lugar lugar) {

        this.valor = valor;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.lugar = lugar;
    }

    /*
        Inicio de getters and setters
     */
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
     * Método equals sobreescrito para la clase Calificacion
     * @param o Objeto a comparar
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calificacion that = (Calificacion) o;

        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
