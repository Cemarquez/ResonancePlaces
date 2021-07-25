package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;

import java.util.List;

public interface AdministradorServicio  {

    Administrador registrarAdministrador(Administrador admin) throws Exception;
    void eliminarAdministrador(Administrador admin) throws Exception;
    Administrador actualizarAdministrador(Administrador admin) throws Exception;
    List<Administrador> listarAdministradores();
    Administrador iniciarSesion(String usuario, String contrasena) throws Exception;
    void crearModerador(Moderador moderador, String adminNickname);
    List<Moderador> listarModeradores(String adminNickname);
    void eliminarModerador(String adminNickname, Moderador moderador);


}
