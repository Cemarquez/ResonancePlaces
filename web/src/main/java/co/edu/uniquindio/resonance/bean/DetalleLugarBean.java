package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleLugarBean  implements Serializable {

    @Value("#{param['lugar']}")
    private String lugarParam;

    @Autowired
    private LugarServicio lugarServicio;

    @Setter
    @Getter
    private Lugar lugar;

    @Setter
    @Getter
    private List<Calificacion> calificaciones;

    @Setter
    @Getter
    private List<Horario> horarios;

    private double valor;
    private String titulo;
    private String mensaje;

    @Value(value = "#{seguridadBean.usuario}")
    private Usuario usuarioLogin;

    @Getter
    @Setter
    private List<String> fotos;

    @Getter
    @Setter
    private String styleBoton = "rounded-button ui-button-danger ui-button-outlined";

    @Getter
    @Setter
    private boolean favorito;

    @Setter
    @Getter
    private Calificacion nuevaCalificacion;

    @PostConstruct
    public void inicializar() {
        if (lugarParam != null && !lugarParam.isEmpty()) {
            try {
                this.favorito = false;
                int id = Integer.parseInt(lugarParam);
                this.lugar = lugarServicio.obtenerLugar(Integer.parseInt(lugarParam));
                this.calificaciones = lugarServicio.listarCalificaciones(id);
                this.horarios = lugarServicio.listarHorarios(id);
                this.fotos = lugar.getFoto();
                this.nuevaCalificacion = new Calificacion();
                if (usuarioLogin != null)
                    if (lugarServicio.obtenerFavorito(lugar, usuarioLogin) != null) {
                        favorito = true;
                        this.styleBoton = "rounded-button ui-button-danger";
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int obtenerCalificacion() {
        int calificacion = 0;
        for (Calificacion c : lugar.getCalificaciones()) {
            calificacion += c.getValor();
        }

        if (lugar.getCalificaciones().isEmpty())
            return calificacion;


            return calificacion / lugar.getCalificaciones().size();


    }
        public void crearComentario () {
            if (usuarioLogin != null) {

                nuevaCalificacion.setLugar(lugar);
                nuevaCalificacion.setUsuario(usuarioLogin);
                lugarServicio.crearCalificacion(nuevaCalificacion);
                nuevaCalificacion = new Calificacion();
            }


        }


        public void marcarFavotiro () {

            if (this.favorito)
                this.styleBoton = "rounded-button ui-button-danger ui-button-outlined";

            else
                this.styleBoton = "rounded-button ui-button-danger";

            favorito = !favorito;

            if (usuarioLogin != null) {
                lugarServicio.marcarFavorito(lugar, usuarioLogin);
            }

        }



}
