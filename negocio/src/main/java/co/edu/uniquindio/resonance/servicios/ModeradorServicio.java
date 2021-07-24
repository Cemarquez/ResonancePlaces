package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;

import java.util.List;

public interface ModeradorServicio {
    Moderador registarModerador(Moderador moderador) throws Exception;
    void eliminarModerador(Moderador moderador) throws Exception;
    Moderador actualizarModerador(Moderador moderador) throws Exception;
    List<Moderador> listarModeradores();
    Moderador iniciarSesion(String usuario, String contrasena) throws Exception;

    List<Lugar> obtenerLugaresSinAprobar();
    void aprobarLugar (int codigoLugar);

    void rechazarLugar(int codigoLugar);


    }

