package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Implementacion de CalificacionServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
@Transactional
public class AdministradorServicioImpl implements  AdministradorServicio, Serializable {

    @Autowired
    private AdministradorRepo administradorRepo;
    @Autowired
    private ModeradorRepo moderadorRepo;
    @Autowired
    private LugarRepo lugarRepo;


    @Override
    public Administrador registrarAdministrador(Administrador admin) throws Exception {
        Administrador administrador = administradorRepo.save(admin);
        return administrador;
    }

    @Override
    public void eliminarAdministrador(Administrador admin) throws Exception {
        Optional<Administrador> buscado = administradorRepo.findById(admin.getNickname());
        if(!buscado.isPresent()){
            throw new Exception("No existe el administrador");
        }

        administradorRepo.delete(admin);
    }

    @Override
    public Administrador actualizarAdministrador(Administrador admin) throws Exception {
        Optional<Administrador> buscado = administradorRepo.findById(admin.getNickname());
        if(!buscado.isPresent()){
            throw new Exception("No existe el administrador");
        }


        Administrador administradorActualizado = administradorRepo.save(admin);
        return administradorActualizado;

    }

    @Override
    public List<Administrador> listarAdministradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador iniciarSesion(String usuario, String contrasena) throws Exception {

        Administrador user = administradorRepo.findByNicknameAndContrasena(usuario, contrasena);

        if (user!=null){
            return user;
        } else {

            user = administradorRepo.findAdministradorByEmailAndContrasena(usuario,contrasena);


        }
        return user;
    }

    @Override
    public void crearModerador(Moderador moderador, String adminNickname) {
        Administrador admin = administradorRepo.findById(adminNickname).get();
        moderador.setAdministrador(admin);
        admin.getModeradores().add(moderador);
        moderadorRepo.save(moderador);
        administradorRepo.save(admin);


    }

    @Override
    public List<Moderador> listarModeradores(String adminNickname) {

            return administradorRepo.listarModeradores(adminNickname);
    }

    @Override
    public void eliminarModerador(String adminNickname, Moderador moderador) {
        Administrador admin = administradorRepo.findByNickname(adminNickname);

        admin.getModeradores().remove(moderador);

        if(moderador.getLugaresRechazados() != null)
        {
            for (Lugar l : moderador.getLugaresRechazados()){

                l.setModerador(null);
                lugarRepo.save(l);
            }
        }

        if(moderador.getLugares() != null)
        {
            for (Lugar l : moderador.getLugares()){

                l.setModerador(null);
                lugarRepo.save(l);
            }
        }

        moderadorRepo.delete(moderador);
        administradorRepo.save(admin);
    }

    @Override
    public List<Reporte1DTO> generarReporte1() {
       List<Reporte1DTO> reportes = administradorRepo.generarReporte1();



        return reportes;
    }

    @Override
    public List<Reporte2DTO> generarReporte2() {
        List<Reporte2DTO> reportes = administradorRepo.generarReporte2();



        return reportes;
    }

    @Override
    public List<Reporte3DTO> generarReporte3() {
        List<Reporte3DTO> reportes = administradorRepo.generarReporte3();



        return reportes;
    }

    @Override
    public Administrador recuperarContrasenia(String admin) throws Exception {

        Administrador administrador = administradorRepo.findByNickname(admin);

        if (administrador!=null){
            return administrador;
        } else {

            administrador = administradorRepo.findByEmail(admin);
        }
        return  administrador;
    }

    @Override
    public List<Reporte4DTO> generarReporte4() {
        List<Reporte4DTO> reportes = administradorRepo.generarReporte4();



        return reportes;
    }
}
