package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.CiudadServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private  UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private SeguridadBean seguridadBean;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Lugar lugar;

    @Getter @Setter
    private Ciudad ciudad;

    @PostConstruct
    public void inicializar(){
        this.usuario = new Usuario();
        this.ciudad = new Ciudad();
        this.lugar = new Lugar();
        ciudades = ciudadServicio.listarCiudades();

    }


    public String registrarUsuario() {
        try {

            usuarioServicio.registrarUsuario(usuario);
            ExecutorService executor = Executors.newFixedThreadPool(10);
            executor.execute(new Runnable() {
                public void run() {
                    EmailBean.sendEmailBienvenida(usuario.getEmail(), usuario.getNickname());
                }
            });

            executor.shutdown();

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            seguridadBean.setEmail(usuario.getNickname());
            seguridadBean.setPassword(usuario.getContrasena());
            return seguridadBean.iniciarSesion();
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }


        return null;
    }

}