package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Telefono;

import java.util.List;

public interface TelefonoServicio {

    Telefono registrarTelefono(Telefono telefono) throws Exception;
    void eliminarTelefono(Telefono telefono) throws Exception;
    Telefono actualizarTelefono(Telefono telefono) throws Exception;
    List<Telefono> listarTelefonos();
}
