package co.edu.uniquindio.resonance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class LugarDTO {

    private int id;
    private double latitud;
    private double longitud;
    private String nombre;
    private String ciudad;
    private String categoria;
    private int contadorId;
    private int distancia;

    public LugarDTO(int id, double latitud, double longitud, String nombre, String ciudad, String categoria, int contadorId) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.categoria = categoria;
        this.contadorId = contadorId;
    }
}
