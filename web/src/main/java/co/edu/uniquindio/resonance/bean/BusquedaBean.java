package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean {

    private String busqueda;

    @Autowired
    private LugarServicio lugarServicio;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    private List<Lugar> lugares;

    @PostConstruct
    public void inicializar() {
        if( busquedaParam!=null && !busquedaParam.isEmpty()) {
            lugares = lugarServicio.buscarLugares(busquedaParam);
        }
    }

    public String buscar() {
        return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getBusquedaParam() {
        return busquedaParam;
    }

    public void setBusquedaParam(String busquedaParam) {
        this.busquedaParam = busquedaParam;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }
}
