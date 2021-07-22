package co.edu.uniquindio.resonance.bean;

import org.primefaces.PrimeFaces;
import org.springframework.web.context.annotation.RequestScope;
import javax.inject.Named;

@Named
@RequestScope
public class SelectBooleanView {

    private boolean value1;
    private boolean value2;

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public void addMessage() {
        PrimeFaces.current().executeScript("console.log('hola')");
    }
}