package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Implementacion de CalificacionServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
public class AdministradorServicioImpl implements  AdministradorServicio, Serializable {

    @Autowired
    private AdministradorRepo administradorRepo;


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
}
