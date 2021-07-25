package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.ModeradorRepo;
import org.dom4j.rule.Mode;
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
    public List<Lugar> obtenerLugaresAprobados(String nicknameModerador) {
        return moderadorRepo.obtenerLugaresAutorizados(nicknameModerador);
    }

    @Override
    public List<Lugar> obtenerLugaresRechazados(String nicknameModerador) {
        return moderadorRepo.obtenerLugaresRechazados(nicknameModerador);
    }

    @Override
    public void aprobarLugar(int codigoLugar,String nicknameModerador) {
        Moderador mod = moderadorRepo.findById(nicknameModerador).get();
        Lugar lugar = lugarRepo.findById(codigoLugar).get();
        lugar.setEstado(true);
        lugar.setRechazado(false);
        lugar.setModerador(mod);
        lugarRepo.save(lugar);
        moderadorRepo.obtenerLugaresAutorizados(nicknameModerador).add(lugar);
        moderadorRepo.save(mod);

    }

    @Override
    public void rechazarLugar(int codigoLugar,String nicknameModerador) {
        Lugar lugar = lugarRepo.findById(codigoLugar).get();
        Moderador mod = moderadorRepo.findById(nicknameModerador).get();
        lugar.setRechazado(true);
        lugar.setEstado(true);
        lugar.setModerador(mod);
        lugarRepo.save(lugar);
        moderadorRepo.obtenerLugaresRechazados(nicknameModerador).add(lugar);
        moderadorRepo.save(mod);

    }


}
