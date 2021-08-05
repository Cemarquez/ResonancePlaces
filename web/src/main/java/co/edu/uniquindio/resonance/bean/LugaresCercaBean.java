package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.dto.LugarDTO;
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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class LugaresCercaBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private List<LugarDTO>  lugaresDTO;

    @Getter @Setter
    private Lugar lugar;

    @Getter @Setter
    private List<Lugar>lugares;

    @Getter @Setter
    private int filtrarDistancia;



    @Value("#{param['lugar']}")
    private String parametro;

    @Getter @Setter
    private List<LugarDTO> lugaresClon;

    @Getter @Setter
    private  double dis;

    @PostConstruct
    public void inicializar(){

        try {

            this.lugares = lugarServicio.listarLugaresAutorizados();
            int cont=0;
            lugaresDTO = new ArrayList<>();
            for(Lugar l: lugares){
                LugarDTO lugarDTO = new LugarDTO(l.getCodigo(), l.getLatitud(), l.getLongitud(), l.getNombre(), l.getCiudad().getNombre(), l.getCategoria().getNombre(), cont);
                lugaresDTO.add(lugarDTO);
                cont++;
            }
            lugaresClon = new ArrayList<>();
            PrimeFaces.current().executeScript("crearMapa(" + new Gson().toJson(lugaresDTO)  + ");");



           // lugar = lugarServicio.obtenerLugar(18);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public String titulo(){
        return "Lugares cerca";
    }

    public void actualizarLugares(){

        filtrarDistancia =Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:txt5")) ;
        if(lugaresClon.isEmpty()){
            for(LugarDTO l : lugaresDTO){
                int distancia =Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:lista:"+l.getContadorId()+":distancia")) ;
                l.setDistancia(distancia);
            }
            lugaresClon.addAll(lugaresDTO);
        }

        if(filtrarDistancia==0){
            lugaresDTO.addAll(lugaresClon);
        }else{
            lugaresDTO.clear();
            for(LugarDTO l : lugaresClon){
                if(l.getDistancia()<=filtrarDistancia){
                    lugaresDTO.add(l);
                }
            }
        }


        PrimeFaces.current().executeScript("ubicarLugaresActualizados(" + new Gson().toJson(lugaresDTO)  + ");");


    }
}
