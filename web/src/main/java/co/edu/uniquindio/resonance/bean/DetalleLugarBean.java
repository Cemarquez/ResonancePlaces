package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.servicios.CalificacionServicio;
import co.edu.uniquindio.resonance.servicios.DenunciaServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Getter @Setter
    private Lugar lugar;


    @Getter @Setter
    private List<Calificacion> calificaciones;


    @Getter @Setter
    private List<Horario> horarios;

    private double valor;
    private String titulo;
    private String mensaje;

    @Value(value = "#{seguridadBean.usuario}")
    private Usuario usuarioLogin;

    @Setter @Getter
    private List<String> fotos;

    @Setter @Getter
    private String styleBoton = "rounded-button ui-button-danger ui-button-outlined";

    @Setter @Getter
    private boolean favorito;

    @Getter @Setter
    private Calificacion nuevaCalificacion;

    @Getter @Setter
    private String respuesta;

    @Getter @Setter
    private String estado;

    @Getter @Setter
    private String estadoStyle;

    @Getter @Setter
    private List<Date> rangoFechasReserva;

    @Getter @Setter
    private  String mensajeContacto;
    @Getter @Setter
    private String asuntoContacto;
    @Getter @Setter
    private String emailContacto;
    @Getter @Setter
    private String motivoDenuncia;
    @Getter @Setter
    private String descripcionDenuncia;

    @PostConstruct
    public void inicializar() {
        if (lugarParam != null && !lugarParam.isEmpty()) {
            try {
                this.rangoFechasReserva = new ArrayList<>();
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
                ExecutorService executor = Executors.newFixedThreadPool(10);
                executor.execute(new Runnable() {
                    public void run() {

                        EmailBean.sendEmailComentario(lugar.getUsuario().getEmail(), usuarioLogin.getNickname(), nuevaCalificacion.getMensaje() , nuevaCalificacion.getTitulo(),lugar.getNombre());
                    }
                });
                nuevaCalificacion = new Calificacion();
                this.calificaciones = lugarServicio.listarCalificaciones(lugar.getCodigo());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                        "Comentario creado!");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
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
                ExecutorService executor = Executors.newFixedThreadPool(10);
                executor.execute(new Runnable() {
                    public void run() {
                        EmailBean.sendEmailRespuesta(c.getUsuario().getEmail(), lugar.getUsuario().getNickname(), c.getMensaje(), c.getTitulo(), lugar.getNombre(), c.getRespuesta());
                    }
                });

                this.calificaciones = lugarServicio.listarCalificaciones(lugar.getCodigo());
                this.respuesta=null;
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                        "Comentario respondido!");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
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

        public void crearReserva(){
            LocalDate fechaInicio = rangoFechasReserva.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = rangoFechasReserva.get(rangoFechasReserva.size()-1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Reserva reserva = new Reserva(fechaInicio, fechaFin, lugar, usuarioLogin );
            usuarioServicio.registrarReserva(reserva);
            rangoFechasReserva = new ArrayList<>();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Reserva exitosa!");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);

        }


        public void enviarMensaje(){

            ExecutorService executor = Executors.newFixedThreadPool(10);
            executor.execute(new Runnable() {
                public void run() {
                    EmailBean.sendEmailContacto(lugar.getUsuario().getEmail(),usuarioLogin.getNickname(),mensajeContacto,asuntoContacto,lugar.getNombre(), emailContacto);
                }
            });
        asuntoContacto = "";
        mensajeContacto= "";
        emailContacto = "";
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Mensaje enviado!");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);

        }


        public void enviarDenuncia(){
            Denuncia denuncia = new Denuncia();
            denuncia.setLugar(lugar);
            denuncia.setUsuario(usuarioLogin);
            denuncia.setMotivo(motivoDenuncia);
            denuncia.setDescripcion(descripcionDenuncia);

            usuarioServicio.registrarDenuncia(denuncia);




        }






}
