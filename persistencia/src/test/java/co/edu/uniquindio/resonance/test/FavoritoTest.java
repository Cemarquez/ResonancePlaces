package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Favorito;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
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
public class FavoritoTest {

    @Autowired
    private FavoritoRepo favoritoRepo;
    @Autowired
    private LugarRepo lugarRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;


    @Test
    public void registrarFavoritoTest(){




        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

        Favorito favorito = new Favorito(usuarioGuardado,lugarGuardado);
        Favorito favoritoRegistrado = favoritoRepo.save(favorito);
        Assertions.assertNotNull(favoritoRegistrado);
    }

    @Test
    public void eliminarFavoritoTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

        Favorito favorito = new Favorito(usuarioGuardado,lugarGuardado);
        Favorito favoritoRegistrado = favoritoRepo.save(favorito);
        favoritoRepo.delete(favoritoRegistrado);
        Favorito buscado = favoritoRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void actualizarFavoritoTest(){



        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

        Favorito favorito = new Favorito(usuarioGuardado,lugarGuardado);
        Favorito favoritoRegistrado = favoritoRepo.save(favorito);
        Assertions.assertNotNull(favoritoRegistrado);

        Usuario usuarioNuevo = new Usuario("Juanito", "Juan", "juan@gmail.com", "juan12345");
        Usuario usuarioNuevoGuardado = usuarioRepo.save(usuarioNuevo);
        Assertions.assertNotNull(usuarioNuevoGuardado);

        System.out.println("FAV ID: " + favorito.getCodigo());
        favoritoRegistrado.setUsuario(usuarioNuevo);


        favoritoRepo.save(favoritoRegistrado);

      Favorito buscado = favoritoRepo.findById(2).orElse(null);
       Assertions.assertEquals("Juanito", buscado.getUsuario().getNickname());

    }

    @Test
    public void listarFavoritos(){
        List<Favorito> lista = favoritoRepo.findAll();

        System.out.println(lista);
    }

    @Test
    @Sql({"classpath:favoritos.sql","classpath:usuarios.sql","classpath:lugares.sql"})
    public void listarFavsTest(){
        List<Favorito> lista = favoritoRepo.findAll();

        System.out.println(lista.get(0).getUsuario().getNickname());
    }


}
