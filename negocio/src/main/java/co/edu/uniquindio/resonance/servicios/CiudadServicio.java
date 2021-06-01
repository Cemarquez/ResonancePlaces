package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Ciudad;

import java.util.List;

public interface CiudadServicio {

    Ciudad registrarCiudad(Ciudad ciudad) throws Exception;
    void eliminarCiudad(Ciudad ciudad) throws Exception;
    Ciudad actualizarCiudad(Ciudad ciudad) throws Exception;
    List<Ciudad> listarCiudades();

}
