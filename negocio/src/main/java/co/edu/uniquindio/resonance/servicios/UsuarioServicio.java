package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.*;

import java.util.List;

/**
 * Interface de UsuarioServicio para Usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;
    Usuario actualizarUsuario(Usuario u) throws Exception;
    void eliminarUsuario(String nickname) throws Exception;
    Usuario obtenerUsuario(String nickname) throws Exception;
    List<Usuario> listarUsuarios();
    Usuario iniciarSesion(String usuario, String contrasena) throws Exception;
    Usuario recuperarContrasenia(String usuario) throws Exception;
    List<Lugar> obtenerLugaresAutorizados(String nickname);
    List<Lugar> obtenerLugaresNoAutorizados(String nickname);
    List<Lugar> obtenerFavoritos (String nickname);
    List<Lugar> obtenerLugaresRechazados(String nickname);
    List<Calificacion> obtenerComentariosSinRespuesta(String nickname);
    Reserva registrarReserva(Reserva reserva);
    void eliminarReserva(Integer id) throws  Exception;
    List<Reserva> obtenerReservas(String nickname) throws  Exception;
    Denuncia registrarDenuncia(Denuncia denuncia);


}
