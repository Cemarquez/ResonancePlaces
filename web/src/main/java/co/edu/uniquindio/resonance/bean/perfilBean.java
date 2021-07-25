package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Favorito;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
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

    @PostConstruct
    public void inicializar() {
        this.lugaresAutorizados = usuarioServicio.obtenerLugaresAutorizados(usuario.getNickname());
        this.favoritos = usuario.getFavoritos();
        this.lugaresNoAutorizados = usuarioServicio.obtenerLugaresNoAutorizados(usuario.getNickname());
        this.lugaresRechazados = usuarioServicio.obtenerLugaresRechazados(usuario.getNickname());
        this.lugaresFavoritos = usuarioServicio.obtenerFavoritos(usuario.getNickname());
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

}