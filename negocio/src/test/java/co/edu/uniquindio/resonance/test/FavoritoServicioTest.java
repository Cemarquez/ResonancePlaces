package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.NegocioApplication;
import co.edu.uniquindio.resonance.entidades.Favorito;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.FavoritoRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import co.edu.uniquindio.resonance.servicios.FavoritoServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase Test para FavoritoServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class FavoritoServicioTest {
    /**
     * Servicio de la entidad favorito
     */
    @Autowired
    private FavoritoServicio favoritoServicio;

    /**
     * Repositorio de la entidad favorito
     */
    @Autowired
    private FavoritoRepo favoritoRepo;
    /**
     * Repositorio de la entidad lugar
     */
    @Autowired
    private LugarRepo lugarRepo;
    /**
     * Repositorio de la entidad usuario
     */
    @Autowired
    private UsuarioRepo usuarioRepo;

    /**
     * Método que permite registrar un favorito en la base de datos en forma de test para verificar su correcto funcionamiento
     */
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
        Favorito favoritoRegistrado = null;
        try {
            favoritoRegistrado = favoritoServicio.registrarFavorito(favorito);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(favoritoRegistrado);
    }

    /**
     * Método que permite eliminar un favorito en la base de datos en forma de test para verificar su correcto funcionamiento
     */
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
        Favorito favoritoRegistrado = null;
        try {
            favoritoRegistrado = favoritoServicio.registrarFavorito(favorito);
            favoritoServicio.eliminarFavorito(favoritoRegistrado);
            Favorito buscado = favoritoRepo.findById(favoritoRegistrado.getCodigo()).orElse(null);
            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Método que permite actualizar un favorito en la base de datos en forma de test para verificar su correcto funcionamiento
     */
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
        Favorito favoritoRegistrado = null;
        try {
            favoritoRegistrado = favoritoServicio.registrarFavorito(favorito);
            Assertions.assertNotNull(favoritoRegistrado);
            Usuario usuarioNuevo = new Usuario("Juanito", "Juan", "juan@gmail.com", "juan12345");
            Usuario usuarioNuevoGuardado = usuarioRepo.save(usuarioNuevo);
            Assertions.assertNotNull(usuarioNuevoGuardado);

            System.out.println("FAV ID: " + favorito.getCodigo());
            favoritoRegistrado.setUsuario(usuarioNuevo);


            favoritoServicio.actualizarFavorito(favoritoRegistrado);

        } catch (Exception e) {
            e.printStackTrace();
        }



        Favorito buscado = favoritoRepo.findById(favoritoRegistrado.getCodigo()).orElse(null);
        Assertions.assertEquals("Juanito", buscado.getUsuario().getNickname());

    }

    /**
     * Método que permite listar  favorito en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void listarFavoritosTest(){
        List<Favorito> lista = favoritoServicio.listarFavoritos();

        System.out.println(lista);
    }


}
