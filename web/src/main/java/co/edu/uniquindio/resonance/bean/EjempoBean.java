package co.edu.uniquindio.resonance.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class EjempoBean implements Serializable {

    private String atributo1;
    private String atributo2;

    public void cambiar(){
        String temp = atributo1;
        atributo1 = atributo2;
        atributo2 = temp;
    }


}
