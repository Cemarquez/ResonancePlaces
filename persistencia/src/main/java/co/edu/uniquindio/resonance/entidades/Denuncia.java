package co.edu.uniquindio.resonance.entidades;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Denuncia {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="codigo" ,nullable = false )
    @EqualsAndHashCode.Include
    private int codigo;


    @ManyToOne
    @JoinColumn(name="codigo_lugar")
    private Lugar lugar;

    @ManyToOne
    @JoinColumn(name = "nickname_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name ="nickname_moderador")
    private Moderador moderador;

    @Column(name="aprobado")
    private boolean aprobado;

    @Column(name="motivo")
    private String motivo;

    @Column(name="descripcion")
    private String descripcion;


}
