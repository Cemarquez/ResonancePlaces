package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.repositorios.CalificacionRepo;
import co.edu.uniquindio.resonance.repositorios.FavoritoRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {


    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CalificacionRepo calificacionRepo;
    @Autowired
    private LugarRepo lugarRepo;
    @Autowired
    private FavoritoRepo favoritoRepo;


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

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosSQLTest(){
        List<Usuario> lista = usuarioRepo.findAll();

        System.out.println(lista.get(0).getNombre());
    }

}
