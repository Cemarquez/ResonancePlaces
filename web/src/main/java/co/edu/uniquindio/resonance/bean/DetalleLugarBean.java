package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class DetalleLugarBean  implements Serializable {

    @Value("#{param['lugar']}")
    private String lugarParam;

    @Autowired
    private   LugarServicio lugarServicio;
    @Getter
    @Setter
    private Lugar lugar;
    @Getter
    @Setter
    private List<Calificacion> calificaciones;
    @Getter
    @Setter
    private List<Horario> horarios;

    private double valor;
    private String titulo;
    private String mensaje;

    @Value(value ="#{seguridadBean.usuario}")
    private Usuario usuarioLogin;

    @Getter @Setter
    private List<String> fotos;

    @PostConstruct
    public void inicializar() {
        if(lugarParam!=null && !lugarParam.isEmpty()) {
            try {
                int id = Integer.parseInt(lugarParam);
               this.lugar = lugarServicio.obtenerLugar( Integer.parseInt( lugarParam ) );
                this.calificaciones = lugarServicio.listarCalificaciones(id);
                this.horarios = lugarServicio.listarHorarios(id);
                this.fotos = lugar.getFoto();
                System.out.println(fotos.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void crearComentario(){

        if (usuarioLogin!=null) {

            Calificacion calificacion = new Calificacion(valor, titulo, mensaje, usuarioLogin, lugar);
            lugarServicio.crearCalificacion(calificacion);
        }


    }

}
