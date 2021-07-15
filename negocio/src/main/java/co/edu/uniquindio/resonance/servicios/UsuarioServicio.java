package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Usuario;

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

}
