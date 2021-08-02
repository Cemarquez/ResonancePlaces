package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.dto.MarketDTO;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class RutaBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private Lugar lugar;

    @Value("#{param['busqueda']}")
    private String parametro;

    @PostConstruct
    public void inicializar(){
       // lugar = lugarServicio.obtenerLugar(Integer.parseInt(parametro));
        try {
            lugar = lugarServicio.obtenerLugar(18);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MarketDTO marker = new MarketDTO(lugar.getCodigo(), lugar.getLatitud(), lugar.getLongitud(), lugar.getNombre());
        PrimeFaces.current().executeScript("crearRuta(" + new Gson().toJson(marker)  + ");");
    }

    public String titulo(){
        return "Ruta de ubicación";
    }
}
