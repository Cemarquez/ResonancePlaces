package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Getter
    @Setter
    private List<Lugar> lugaresSinAprobar;

    @PostConstruct
    public void inicializar()
    {

      lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();

    }

    public String irAlDetalle(Integer id){
        return "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }

    public void aprobarLugar(Integer id){

        moderadorServicio.aprobarLugar(id);
        lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();
    }

    public void rechazarLugar(Integer id){
        moderadorServicio.rechazarLugar(id);
        lugaresSinAprobar =   moderadorServicio.obtenerLugaresSinAprobar();
    }


}
