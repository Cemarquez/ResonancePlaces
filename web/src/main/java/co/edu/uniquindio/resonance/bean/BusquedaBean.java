package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.dto.MarketDTO;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class BusquedaBean {

    @Getter @Setter
    private String busqueda;

    @Autowired
    private LugarServicio lugarServicio;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Lugar> lugares;

    @Getter @Setter
    private List<Lugar> lugaresFiltrados;


    @PostConstruct
    public void inicializar() {
        if( busquedaParam!=null && !busquedaParam.isEmpty()) {
            lugaresFiltrados = new ArrayList<>();
            lugares = lugarServicio.buscarLugares(busquedaParam);
            List<MarketDTO> markers = this.lugaresFiltrados.stream().map(l -> new MarketDTO(l.getCodigo(), l.getLatitud(), l.getLongitud(), l.getNombre())).collect(Collectors.toList());
            PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(markers)  + ");");
        }
    }

    public String irAlDetalle() {
        return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }

    public String obtenerEstado(Lugar lugar){
        if(isAbierto(lugar)){
            return "Abierto";
        }
        return "Cerrado";
    }

    public boolean isAbierto(Lugar lugar){
        Date date = new Date();
        List<Horario> horarios = lugarServicio.listarHorarios(lugar.getCodigo());

        for(Horario h : horarios){
            if(h.getDia().equalsIgnoreCase(obtenerDia(date))){
                LocalTime horaActual = LocalTime.now(ZoneId.of("America/Bogota"));
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


    public String irAlDetalleLugar(Integer id){
        return "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }

}
