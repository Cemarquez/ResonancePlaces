package co.edu.uniquindio.resonance.bean;
import co.edu.uniquindio.resonance.dto.MarketDTO;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class InicioBean implements Serializable {
    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private List<Lugar> lugares;

    @PostConstruct
    public void inicializar()
    {
        this.lugares = lugarServicio.listarLugaresAutorizados();

        List<MarketDTO> markers = this.lugares.stream().map(l -> new MarketDTO(l.getCodigo(), l.getLatitud(), l.getLongitud(), l.getNombre())).collect(Collectors.toList());
        PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(markers)  + ");");

    }


    public String irAlDetalle(Integer id){
        return "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }
}