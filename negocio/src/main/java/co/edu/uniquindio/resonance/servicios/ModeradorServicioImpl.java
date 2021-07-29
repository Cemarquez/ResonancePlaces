package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.repositorios.DenunciaRepo;
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
    @Autowired
    private DenunciaRepo denunciaRepo;

    @Override
    public Moderador registarModerador(Moderador moderador) throws Exception {
        return moderadorRepo.save(moderador);
    }

    @Override
    public void eliminarModerador(Moderador moderador) throws Exception {

    }

    /**
     * Método que actualiza moderador en la capa de servicios
     * @param m
     * @return
     * @throws Exception
     */
    @Override
    public Moderador actualizarModerador(Moderador m) throws Exception {

        Moderador buscado = moderadorRepo.buscarModPorNickname(m.getNickname());
        boolean sameEmail = false;
        boolean sameNick = false;
        if (m.getNickname().equals(buscado.getNickname())){
            sameNick = true;
        }

        if (m.getEmail().equals((buscado.getEmail()))){
            sameEmail = true;
        }


        if(buscado!=null && sameNick==false){
            throw new Exception("El nickname ya se encuentra en uso");
        }

        if(!estaDisponible(m.getEmail()) && sameEmail==false) {
            throw new Exception("El email ya se encuentra en uso");
        }

        if (m.getNickname().length()>25){
            throw new Exception("El nickname no puede exceder los 25 caracteres");
        }

        if (m.getNombre().length()>70){
            throw new Exception("El nombre no puede exceder 25 caracteres");
        }

        moderadorRepo.save(m);

        return m;
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

    @Override
    public void aprobarDenuncia(Denuncia denuncia) {

        denuncia.setAprobado(true);
        Lugar lugar = denuncia.getLugar();

        lugar.setRechazado(true);
        lugar.setEstado(true);
        lugar.setModerador(denuncia.getModerador());

        lugarRepo.save(lugar);

        List <Denuncia> denunciasSinAprobar = denunciaRepo.obtenerDenunciasSinAprobar();
        for (int i=0; i<denunciasSinAprobar.size();i++ ){
            if (denunciasSinAprobar.get(i).getLugar()== denuncia.getLugar()){
                denuncia.setAprobado(true);
                denuncia.setModerador(denuncia.getModerador());


            }




        }

        denunciaRepo.save(denuncia);













    }

    @Override
    public void rechazarDenuncia(Denuncia denuncia) {
        denuncia.setAprobado(false);

        denunciaRepo.save(denuncia);
    }

    @Override
    public Moderador recuperarContrasenia(String mod) throws Exception {

        Moderador moderador = moderadorRepo.buscarModPorNickname(mod);

        if (moderador!=null){
            return moderador;
        } else {

            moderador = moderadorRepo.buscarModPorEmail(mod);
        }
        return  moderador;
    }

    /**
     * Método que verifica si un email esta disponible
     * @param email
     * @return
     */
    public boolean estaDisponible(String email){
        Moderador moderador = moderadorRepo.buscarModPorEmail(email);
        if(moderador != null)
            return false;
        else
            return true;

    }
}
