package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Ciudad;

public class Consulta7DTO {

    private Ciudad ciudad;
    private Long cantidad;


    public Consulta7DTO(Ciudad ciudad, Long cantidad) {
        this.ciudad = ciudad;
        this.cantidad = cantidad;

    }

    @Override
    public String toString() {
        return ciudad.getNombre() + ", " + cantidad;
    }
}
