package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.NegocioApplication;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioRepo usuarioRepo;


    /**
     * Método que permite registrar un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarUsuarioTest() {

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");

        Usuario usuarioGuardado = null;
        try {
            usuarioGuardado = usuarioServicio.registrarUsuario(usuario);
            Assertions.assertNotNull(usuarioGuardado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que permite eliminar un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarUsuarioTest() {
        try {
            Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
            Usuario registrado = usuarioServicio.registrarUsuario(usuario);
            usuarioServicio.eliminarUsuario("PedroNavaja");
            Usuario buscado = usuarioRepo.findById("PedroNavaja").orElse(null);
            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que permite actualizar un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarUsuarioTest(){

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario registrado = null;
        try {
            registrado = usuarioServicio.registrarUsuario(usuario);
            registrado.setNombre("Juanita Lopez");
            usuarioRepo.save(registrado);
            Usuario buscado = usuarioRepo.findById("PedroNavaja").orElse(null);
            Assertions.assertEquals("Juanita Lopez", buscado.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}