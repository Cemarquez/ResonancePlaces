package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.CalificacionServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Component
@ViewScoped
public class DetalleLugarBean  implements Serializable {

    @Value("#{param['lugar']}")
    private String lugarParam;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private CalificacionServicio calificacionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

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

    @Getter @Setter
    private String respuesta;

    @Getter @Setter
    private String estado;

    @Getter @Setter
    private String estadoStyle;

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
                this.estado ="Cerrado";
                    this.estadoStyle= "estado-lugar-cerrado";
                if(isAbierto()) {
                    this.estado = "Abierto";
                    this.estadoStyle = "estado-lugar-abierto";
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
                EmailBean.sendEmailComentario(lugar.getUsuario().getEmail(), usuarioLogin.getNickname(), nuevaCalificacion.getMensaje() , nuevaCalificacion.getTitulo(),lugar.getNombre());
                nuevaCalificacion = new Calificacion();
                this.calificaciones = lugarServicio.listarCalificaciones(lugar.getCodigo());
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

        public void crearRespuesta(Integer id){
            Calificacion c = calificacionServicio.obtenerCalificacion(id);
            c.setRespuesta(respuesta);
            try {
                calificacionServicio.actualizarCalificacion(c);
                EmailBean.sendEmailRespuesta(c.getUsuario().getEmail(), lugar.getUsuario().getNickname(), c.getMensaje(), c.getTitulo(), lugar.getNombre(), c.getRespuesta());
                this.calificaciones = lugarServicio.listarCalificaciones(lugar.getCodigo());
                this.respuesta=null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public boolean isDuenio(){
        if(usuarioLogin!=null)
            if(usuarioLogin.getNickname().equals(lugar.getUsuario().getNickname()))
                return true;

            return false;
        }

        public boolean isAbierto(){
            Date date = new Date();
            for(Horario h : horarios){
                if(h.getDia().equalsIgnoreCase(obtenerDia(date))){
                    LocalTime horaActual = LocalTime.now(ZoneId.of("America/Bogota"));
                    System.out.println(h.getHoraInicio() + ", " + h.getHoraCierre() + ", " + horaActual +", " + ZoneId.systemDefault());
                    if(h.getHoraInicio().compareTo(horaActual) <0 && h.getHoraCierre().compareTo(horaActual)>0){
                        return true;
                    }
                }
            }
            return false;
        }

        public String obtenerDia(Date date){
            String DIA[] = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
            Calendar calendario = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("America/Bogota")));

            return DIA[calendario.get(Calendar.DAY_OF_WEEK) - 1];
        }

}
