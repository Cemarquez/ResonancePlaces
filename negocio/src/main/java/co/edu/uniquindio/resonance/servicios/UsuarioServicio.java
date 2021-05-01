package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;
    Usuario actualizarUsuario(Usuario u) throws Exception;
    void eliminarUsuario(String cedula) throws Exception;
    List<Usuario> listarUsuarios();
}
