package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.*;

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

    void aprobarDenuncia(Denuncia denuncia);
    void rechazarDenuncia(Denuncia denuncia);


    }

