package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Reporte;
import co.edu.uniquindio.resonance.servicios.AdministradorServicio;
import co.edu.uniquindio.resonance.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class AdministradorBean {
    @Autowired
    private AdministradorServicio administradorServicio;
    @Getter @Setter
    private Moderador moderador;
    @Value(value = "#{seguridadBean.administrador}")
    @Getter @Setter
    private Administrador administradorLogin;
    @Getter @Setter
    private List<Moderador> moderadores ;
    @Getter @Setter
    protected List<Reporte> reportes;


    @PostConstruct
    public void inicializar()
    {

        this.moderadores =administradorServicio.listarModeradores(administradorLogin.getNickname());
        this.moderador = new Moderador();
    }


    public void crearModerador(){
        try {
           administradorServicio.crearModerador(moderador,administradorLogin.getNickname());
            this.moderadores =administradorServicio.listarModeradores(administradorLogin.getNickname());

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public void eliminarModerador(Moderador mod){

        try {
            administradorServicio.eliminarModerador(administradorLogin.getNickname(),mod);
            this.moderadores =administradorServicio.listarModeradores(administradorLogin.getNickname());
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Moderador eliminado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }


}
