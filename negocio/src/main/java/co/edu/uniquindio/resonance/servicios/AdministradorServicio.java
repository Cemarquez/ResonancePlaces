package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.repositorios.Reporte1DTO;
import co.edu.uniquindio.resonance.repositorios.Reporte2DTO;
import co.edu.uniquindio.resonance.repositorios.Reporte3DTO;
import co.edu.uniquindio.resonance.repositorios.Reporte4DTO;

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
    Administrador recuperarContrasenia(String admin) throws Exception;
    List<Reporte1DTO>generarReporte1();
    List<Reporte2DTO>generarReporte2();
    List<Reporte3DTO>generarReporte3();
    List<Reporte4DTO>generarReporte4();


}
