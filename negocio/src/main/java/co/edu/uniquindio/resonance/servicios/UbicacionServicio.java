package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Ubicacion;

import java.util.List;

public interface UbicacionServicio {
    Ubicacion registarUbicacion(Ubicacion ubicacion) throws Exception;
    void eliminarUbicacion(Ubicacion ubicacion) throws Exception;
    Ubicacion actualizarUbicacion(Ubicacion ubicacion) throws Exception;
    List<Ubicacion> listarUbicaciones();
}
