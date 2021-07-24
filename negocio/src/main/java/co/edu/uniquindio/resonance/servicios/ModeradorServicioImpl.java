package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.ModeradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModeradorServicioImpl implements ModeradorServicio {

    @Autowired
    private ModeradorRepo moderadorRepo;
    @Autowired
    private LugarRepo lugarRepo;

    @Override
    public Moderador registarModerador(Moderador moderador) throws Exception {
        return moderadorRepo.save(moderador);
    }

    @Override
    public void eliminarModerador(Moderador moderador) throws Exception {

    }

    @Override
    public Moderador actualizarModerador(Moderador moderador) throws Exception {
        return null;
    }

    @Override
    public List<Moderador> listarModeradores() {
        return null;
    }

    @Override
    public Moderador iniciarSesion(String usuario, String contrasena) throws Exception {
        Moderador user = moderadorRepo.findByNicknameAndContrasena(usuario, contrasena);

        if (user!=null){
            return user;
        } else {

            user = moderadorRepo.findAdministradorByEmailAndContrasena(usuario,contrasena);
        }

        return user;
    }

    @Override
    public List<Lugar> obtenerLugaresSinAprobar() {



        return moderadorRepo.obtenerLugaresSinAprobar();
    }

    @Override
    public void aprobarLugar(int codigoLugar) {
        Lugar lugar = lugarRepo.findById(codigoLugar).get();
        lugar.setEstado(true);
        lugar.setRechazado(false);

        lugarRepo.save(lugar);

    }

    @Override
    public void rechazarLugar(int codigoLugar) {
        Lugar lugar = lugarRepo.findById(codigoLugar).get();
        lugar.setRechazado(true);
        lugar.setEstado(true);
        lugarRepo.save(lugar);

    }


}
