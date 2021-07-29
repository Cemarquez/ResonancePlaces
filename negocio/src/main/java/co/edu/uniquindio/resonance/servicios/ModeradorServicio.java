package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Usuario;

import java.util.List;

public interface ModeradorServicio {
    Moderador registarModerador(Moderador moderador) throws Exception;
    void eliminarModerador(Moderador moderador) throws Exception;
    Moderador actualizarModerador(Moderador moderador) throws Exception;
    List<Moderador> listarModeradores();
    Moderador iniciarSesion(String usuario, String contrasena) throws Exception;

    List<Lugar> obtenerLugaresSinAprobar();

    List<Lugar> obtenerLugaresAprobados(String nicknameModerador);
    List<Lugar> obtenerLugaresRechazados(String nicknameModerador);

    Moderador recuperarContrasenia(String moderador) throws Exception;
    void aprobarLugar (int codigoLugar, String nicknameModerador);

    void rechazarLugar(int codigoLugar,String nicknameModerador);


    }

