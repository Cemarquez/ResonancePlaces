package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;
@Component
@ViewScoped
public class ModeradorBean {

    @Autowired
    private LugarServicio lugarServicio;
    @Autowired
    private ModeradorServicio moderadorServicio;

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

    @PostConstruct
    public void inicializar()
    {

      lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();
      lugaresAprobados = moderadorServicio.obtenerLugaresAprobados(getModeradorLogin().getNickname());
      lugaresRechazados = moderadorServicio.obtenerLugaresRechazados(getModeradorLogin().getNickname());


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


}
