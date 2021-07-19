package co.edu.uniquindio.resonance.converter;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.servicios.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

@Component
public class CategoriaConverter implements Converter<Categoria>, Serializable {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        Categoria c = null;
        if (s != null && !"".equals(s)) {
            try {
                c = categoriaServicio.obtenerCategoria(Integer.parseInt(s)); //Hacer casting si es necesario
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(uiComponent.getClientId() + ":id no válido"));
            }
        }
        return c;

    }
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {
        if(categoria!=null){
            return "" + categoria.getCodigo();
        }

        return "";
    }
}
