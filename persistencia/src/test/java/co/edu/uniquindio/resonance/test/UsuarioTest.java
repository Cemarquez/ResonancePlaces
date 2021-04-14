package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {


    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarUsuarioTest() {

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");

        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado);


    }

    @Test
    public void eliminarUsuarioTest() {
        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario registrado = usuarioRepo.save(usuario);

        usuarioRepo.delete(registrado);
        Usuario buscado = usuarioRepo.findById("PedroNavaja").orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void actualizarUsuarioTest(){
        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario registrado = usuarioRepo.save(usuario);

        registrado.setNombre("Juanita Lopez");

        usuarioRepo.save(registrado);

        Usuario buscado = usuarioRepo.findById("PedroNavaja").orElse(null);
        Assertions.assertEquals("Juanita Lopez", buscado.getNombre());
    }

    @Test
    public void listarUsuariosTest(){

        List<Usuario> lista = usuarioRepo.findAll();

        System.out.println(lista);
    }


}
