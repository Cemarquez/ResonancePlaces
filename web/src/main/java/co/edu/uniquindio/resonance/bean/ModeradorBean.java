package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Denuncia;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.DenunciaServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@ViewScoped
public class ModeradorBean {

    @Autowired
    private LugarServicio lugarServicio;
    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private DenunciaServicio denunciaServicio;

    @Value(value = "#{seguridadBean.moderador}")
    @Getter @Setter
    private Moderador moderadorLogin;

    @Getter
    @Setter
    private List<Lugar> lugaresSinAprobar;

    @Getter
    @Setter
    private List<Lugar> lugaresAprobados;

    @Getter
    @Setter
    private List<Lugar> lugaresRechazados;

    @Getter @Setter
    private List<Denuncia> denunciasSinAprobar;


    @PostConstruct
    public void inicializar()
    {

      lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();
      lugaresAprobados = moderadorServicio.obtenerLugaresAprobados(getModeradorLogin().getNickname());
      lugaresRechazados = moderadorServicio.obtenerLugaresRechazados(getModeradorLogin().getNickname());
      denunciasSinAprobar = denunciaServicio.obtenerDenunciasSinAprobar();

    }

    public String irAlDetalle(Integer id){
        return "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }

    public void aprobarLugar(Integer id, String nicknameModerador){



        moderadorServicio.aprobarLugar(id,nicknameModerador);
        lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();
        lugaresAprobados = moderadorServicio.obtenerLugaresAprobados(nicknameModerador);
        lugaresRechazados = moderadorServicio.obtenerLugaresRechazados(nicknameModerador);




    }

    public void rechazarLugar(Integer id,String nicknameModerador){
        moderadorServicio.rechazarLugar(id, nicknameModerador);
        lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();
        lugaresRechazados = moderadorServicio.obtenerLugaresRechazados(nicknameModerador);
        lugaresAprobados = moderadorServicio.obtenerLugaresAprobados(nicknameModerador);


    }

    public void actualizarModerador() throws Exception {

        try {
            moderadorServicio.actualizarModerador(moderadorLogin);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Actualización exitosa");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void aprobarDenuncia(Denuncia denuncia){

        denuncia.setModerador(moderadorLogin);
        moderadorServicio.aprobarDenuncia(denuncia);
        denunciasSinAprobar = denunciaServicio.obtenerDenunciasSinAprobar();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new Runnable() {
            public void run() {
                EmailBean.sendEmailDenuncia(denuncia.getLugar().getUsuario().getEmail(), denuncia.getDescripcion(), denuncia.getMotivo(), denuncia.getLugar().getNombre());
            }
        });

        executor.shutdown();

    }

    public void rechazarDenuncia(Denuncia denuncia){
        denuncia.setModerador(moderadorLogin);
        moderadorServicio.rechazarDenuncia(denuncia);
        denunciasSinAprobar = denunciaServicio.obtenerDenunciasSinAprobar();

    }


}
