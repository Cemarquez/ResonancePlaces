package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Moderador;

import java.util.List;

public interface ModeradorServicio {
    Moderador registarModerador(Moderador moderador) throws Exception;
    void eliminarModerador(Moderador moderador) throws Exception;
    Moderador actualizarModerador(Moderador moderador) throws Exception;
    List<Moderador> listarModeradores();
}
