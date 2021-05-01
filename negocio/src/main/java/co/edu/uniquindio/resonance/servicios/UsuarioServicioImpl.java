package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{
    private final UsuarioRepo usuarioRepo;
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getNickname());
        if(buscado.isPresent()){
            throw new Exception("El nickname ya se encuentra en uso");
        }
        if(!estaDisponible(u.getEmail())){
            throw new Exception("El email ya se encuentra en uso");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        return null;
    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {

    }

    @Override
    public List<Usuario> listarUsuarios() {
        return null;
    }

    public boolean estaDisponible(String email){
        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);
        return usuario.isEmpty();
    }


}