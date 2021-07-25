package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.AdministradorRepo;
import co.edu.uniquindio.resonance.repositorios.ModeradorRepo;
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
        Administrador admin = administradorRepo.findById(adminNickname).get();
        admin.getModeradores().remove(moderador);
        moderadorRepo.delete(moderador);
        administradorRepo.save(admin);


    }
}
