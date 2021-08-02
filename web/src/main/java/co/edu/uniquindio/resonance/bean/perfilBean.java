package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.servicios.CalificacionServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@ViewScoped
public class perfilBean implements Serializable {

    @Value(value ="#{seguridadBean.usuario}")
    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private List<Favorito> favoritos;

    @Getter @Setter
    private List<Lugar> lugaresAutorizados;

    @Getter @Setter
    private List<Lugar> lugaresNoAutorizados;

    @Getter @Setter
    private List<Lugar> lugaresRechazados;

    @Getter @Setter
    private List<Lugar> lugaresFavoritos;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Setter @Getter
    private List<Calificacion> calificacionesSinRespuesta;

    @Autowired
    private CalificacionServicio calificacionServicio;


    @Getter @Setter
    private String respuesta;

    @Getter @Setter
    private List<Reserva> reservas;

    @PostConstruct
    public void inicializar() {
        this.reservas = usuarioServicio.obtenerReservas(usuario.getNickname());
        this.calificacionesSinRespuesta = usuarioServicio.obtenerComentariosSinRespuesta(usuario.getNickname());
        this.favoritos = usuario.getFavoritos();
        this.lugaresAutorizados = usuarioServicio.obtenerLugaresAutorizados(usuario.getNickname());
        this.lugaresNoAutorizados = usuarioServicio.obtenerLugaresNoAutorizados(usuario.getNickname());
        this.lugaresRechazados = usuarioServicio.obtenerLugaresRechazados(usuario.getNickname());
        this.lugaresFavoritos = usuarioServicio.obtenerFavoritos(usuario.getNickname());
    }

    public void eliminarLugar(Integer id){

        try {
            Lugar lugar = lugarServicio.obtenerLugar(id);
            lugarServicio.eliminarLugar(lugar);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Lugar eliminado exitosamente!");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            this.lugaresAutorizados = usuarioServicio.obtenerLugaresAutorizados(usuario.getNickname());
            this.lugaresNoAutorizados = usuarioServicio.obtenerLugaresNoAutorizados(usuario.getNickname());
            this.lugaresRechazados = usuarioServicio.obtenerLugaresRechazados(usuario.getNickname());
            this.lugaresFavoritos = usuarioServicio.obtenerFavoritos(usuario.getNickname());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void actualizarUsuario() throws Exception {

        try {
            usuarioServicio.actualizarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Actualización exitosa");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPendientes(){
        if (calificacionesSinRespuesta.size() > 0)
            return true;
        else
            return false;
    }

    public void crearRespuesta(Integer id){
        Calificacion c = calificacionServicio.obtenerCalificacion(id);
        c.setRespuesta(respuesta);


        try {
            calificacionServicio.actualizarCalificacion(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
            executor.execute(new Runnable() {
                public void run() {

                    EmailBean.sendEmailRespuesta(c.getUsuario().getEmail(), c.getLugar().getUsuario().getNickname(), c.getMensaje(), c.getTitulo(), c.getLugar().getNombre(), c.getRespuesta());

                }
            });
            executor.shutdown();
            this.calificacionesSinRespuesta = usuarioServicio.obtenerComentariosSinRespuesta(usuario.getNickname());
            this.respuesta=null;
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Respuesta enviada!");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void borrarReserva(Integer id){
        usuarioServicio.eliminarReserva(id);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                "Reserva eliminada!");
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        this.reservas = usuarioServicio.obtenerReservas(usuario.getNickname());

    }
}
