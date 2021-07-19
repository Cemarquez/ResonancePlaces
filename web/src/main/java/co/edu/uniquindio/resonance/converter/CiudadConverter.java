package co.edu.uniquindio.resonance.converter;

import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.repositorios.CiudadRepo;
import co.edu.uniquindio.resonance.servicios.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.management.relation.Role;
import java.io.Serializable;

@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {
    @Autowired
    private CiudadServicio ciudadServicio;


    @Override
    public Ciudad getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        Ciudad c = null;
        if (s != null && !"".equals(s)) {
            try {
                c = ciudadServicio.obtenerCiudad(Integer.parseInt(s)); //Hacer casting si es necesario
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(uiComponent.getClientId() + ":id no válido"));
            }
        }
        return c;

    }
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Ciudad ciudad) {
        if(ciudad!=null){
            return "" + ciudad.getCodigo();
        }

        return "";
    }
}