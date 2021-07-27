package co.edu.uniquindio.resonance.bean;

import java.io.Serializable;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.AdministradorServicio;
import co.edu.uniquindio.resonance.servicios.ModeradorServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private Administrador administrador;

    @Getter @Setter
    private Moderador moderador;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    @NotBlank
    private String email,password;

    @Getter @Setter
    private String rol;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    public String iniciarSesion() {

        if (email != null && password!=null){


            try {
                usuario = usuarioServicio.iniciarSesion(email,password);
                administrador = administradorServicio.iniciarSesion(email,password);
                moderador = moderadorServicio.iniciarSesion(email,password);

                if (administrador!=null){

                    rol = "admin";
                    autenticado=true;
                    return "/index?faces-redirect=true";
                } else if (moderador!=null){
                    autenticado=true;
                    rol = "moderador";
                    System.out.println(rol);
                    return "/moderador/aprobarLugar?faces-redirect=true";
                } else if (usuario!=null){
                    autenticado=true;
                    rol = "usuario";
                    return "/index?faces-redirect=true";
                } else{
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                            "¡Usuario o contraseña incorrectos!");
                    FacesContext.getCurrentInstance().addMessage("login-bean",facesMsg);
                    return null;
                }



            } catch (Exception e) {

                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",m);
            }







        }

    return null;
    }

    public String recuperarContrasenia() {

        if (email != null){

            try {

                usuario = usuarioServicio.recuperarContrasenia(email);

               if (usuario!=null){
                   usuario.setContrasena(EmailBean.getPassword());
                   usuarioServicio.actualizarUsuario(usuario);
                   EmailBean.sendEmailContraseña(usuario.getEmail(), usuario.getContrasena());
                   FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                           "¡Contraseña enviada a tu email!");
                   FacesContext.getCurrentInstance().addMessage("olvidaste-bean", facesMsg);
                    return null;
                } else{
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                            "¡Dirección de correo o usuario incorrecto!");
                    FacesContext.getCurrentInstance().addMessage("olvidaste-bean",facesMsg);
                    return null;
                }



            } catch (Exception e) {

                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("olvidaste-bean",m);
            }

        }

        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

}
