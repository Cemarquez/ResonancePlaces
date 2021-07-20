package co.edu.uniquindio.resonance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class MarketDTO {

    private double latitud;
    private double longitud;
    private String nombre;
}
