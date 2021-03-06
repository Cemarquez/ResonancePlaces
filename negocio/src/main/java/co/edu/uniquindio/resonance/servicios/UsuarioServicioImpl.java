package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.repositorios.DenunciaRepo;
import co.edu.uniquindio.resonance.repositorios.ReservaRepo;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase de implementación de UsuarioServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private ReservaRepo reservaRepo;


    @Autowired
    private DenunciaRepo denunciaRepo;

    private final UsuarioRepo usuarioRepo;
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Método que registra usuario en la capa de servicios
     * @param u
     * @return
     * @throws Exception
     */
    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getNickname());
        if(buscado.isPresent()){
            throw new Exception("El nickname ya se encuentra en uso");
        }
        if(!estaDisponible(u.getEmail())){
            throw new Exception("El email ya se encuentra en uso");
        }

        if (u.getNickname().length()>25){
            throw new Exception("El nickname no puede exceder los 25 caracteres");
        }

        if (u.getNombre().length()>70){
            throw new Exception("El nombre no puede exceder 25 caracteres");
        }


        return usuarioRepo.save(u);
    }

    /**
     * Método que actualiza usuario en la capa de servicios
     * @param u
     * @return
     * @throws Exception
     */
    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Usuario buscado = usuarioRepo.findByNickname(u.getNickname());
        boolean sameEmail = false;
        boolean sameNick = false;
        if (u.getNickname().equals(buscado.getNickname())){
            sameNick = true;
        }

        if (u.getEmail().equals((buscado.getEmail()))){
            sameEmail = true;
        }


        if(buscado!=null && sameNick==false){
            throw new Exception("El nickname ya se encuentra en uso");
        }

        if(!estaDisponible(u.getEmail()) && sameEmail==false) {
            throw new Exception("El email ya se encuentra en uso");
        }

        if (u.getNickname().length()>25){
            throw new Exception("El nickname no puede exceder los 25 caracteres");
        }

        if (u.getNombre().length()>70){
            throw new Exception("El nombre no puede exceder 25 caracteres");
        }

        usuarioRepo.save(u);

        return u;
    }

    /**
     * Método que elimina usuario en la capa de servicios
     * @param nickname
     * @throws Exception
     */
    @Override
    public void eliminarUsuario(String nickname) throws Exception {

        Usuario user = usuarioRepo.findByNickname(nickname);

        if (user!=null){
            usuarioRepo.delete(user);
        }else{
            throw new Exception("El usuario que desea eliminar no existe");
        }

    }

    @Override
    public Usuario obtenerUsuario(String nickname) throws Exception {
        return null;
    }

    /**
     * Método que inicia sesion en la aplicacion
     * @param usuario
     * @param contrasena
     * @return
     * @throws Exception
     */

    @Override
    public Usuario iniciarSesion(String usuario, String contrasena) throws Exception {

        Usuario user = usuarioRepo.findByNicknameAndContrasena(usuario, contrasena);

        if (user!=null){
            return user;
        } else {

            user = usuarioRepo.findByEmailAndContrasena(usuario,contrasena);



        }
        return  user;
    }

    @Override
    public Usuario recuperarContrasenia(String usuario) throws Exception {

        Usuario user = usuarioRepo.findByNickname(usuario);

        if (user!=null){
            return user;
        } else {

            user = usuarioRepo.findByEmail(usuario);
        }
        return  user;
    }

    @Override
    public List<Lugar> obtenerLugaresAutorizados(String nickname) {
        return usuarioRepo.obtenerLugaresAutorizados(nickname);
    }

    @Override
    public List<Lugar> obtenerLugaresNoAutorizados(String nickname) {
        return usuarioRepo.obtenerLugaresNoAutorizados(nickname);
    }

    @Override
    public List<Lugar> obtenerLugaresRechazados(String nickname) {
        return usuarioRepo.obtenerLugaresRechazados(nickname);
    }

    @Override
    public List<Calificacion> obtenerComentariosSinRespuesta(String nickname) {
        return usuarioRepo.obtenerComentariosSinRespuesta(nickname);
    }

    @Override
    public Reserva registrarReserva(Reserva reserva) {
        return reservaRepo.save(reserva);
    }

    @Override
    public void eliminarReserva(Integer id) throws Exception {

        Reserva reserva = reservaRepo.findById(id).get();
        if(reserva == null)
        {
            throw new Exception("La reserva no existe");
        }
        reservaRepo.delete(reserva);
    }

    @Override
    public List<Reserva> obtenerReservas(String nickname) throws Exception{
        List<Reserva> reservas =reservaRepo.obtenerReservas(nickname);
        if(reservas.isEmpty())
            throw new Exception("No hay reservas para este usuario");

        return reservas;
    }

    @Override
    public Denuncia registrarDenuncia(Denuncia denuncia) {
        return denunciaRepo.save(denuncia);
    }


    @Override
    public List<Lugar> obtenerFavoritos(String nickname) {

        return usuarioRepo.obtenerLugaresFavoritos(nickname);
    }

    /**
     * Método que lista usuarios de la aplicacion
     * @return
     */
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    /**
     * Método que verifica si un email esta disponible
     * @param email
     * @return
     */
    public boolean estaDisponible(String email){
        Usuario usuario = usuarioRepo.findByEmail(email);
        if(usuario != null)
            return false;
        else
            return true;

    }


}