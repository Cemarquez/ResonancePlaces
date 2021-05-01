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
/**
 * Clase Test para la entidad usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    /**
     * Repositorio de la entidad usuario
     */
    @Autowired
    private UsuarioRepo usuarioRepo;
    /**
     * Repositorio de la entidad calificacion
     */
    @Autowired
    private CalificacionRepo calificacionRepo;
    /**
     * Repositorio de la entidad lugar
     */
    @Autowired
    private LugarRepo lugarRepo;
    /**
     * Repositorio de la entidad favorito
     */
    @Autowired
    private FavoritoRepo favoritoRepo;

    /**
     * Método que permite registrar un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarUsuarioTest() {

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);


    }
    /**
     * Método que permite eliminar un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarUsuarioTest() {

        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario registrado = usuarioRepo.save(usuario);

        usuarioRepo.delete(registrado);
        Usuario buscado = usuarioRepo.findById("PedroNavaja").orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * Método que permite actualizar un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarUsuarioTest(){
        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario registrado = usuarioRepo.save(usuario);

        registrado.setNombre("Juanita Lopez");

        usuarioRepo.save(registrado);

        Usuario buscado = usuarioRepo.findById("PedroNavaja").orElse(null);
        Assertions.assertEquals("Juanita Lopez", buscado.getNombre());

    }
    /**
     * Método que permite listar  usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void listarUsuariosTest(){

        List<Usuario> lista = usuarioRepo.findAll();

        System.out.println(lista);
    }
    /**
     * Método que permite listar  usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosSQLTest(){
        List<Usuario> lista = usuarioRepo.findAll();

        System.out.println(lista.get(0).getNombre());
    }


    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql","classpath:usuarios.sql"})
    public void listar(){
    List<Lugar> lugares = usuarioRepo.obtenerLugares("PedroNavaja");

    for(Lugar l : lugares){
        System.out.println(l.getNombre());
    }
    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql","classpath:lugares.sql" ,"classpath:telefonos.sql","classpath:favoritos.sql"})
    public void  obtenerLugaresFavoritosTest(){

        List <Lugar> favoritos = usuarioRepo.obtenerLugaresFavoritos("Miyagi");

        String t =  favoritos.get(0).getNombre();

        System.out.println("Favorito encontrado: " + t );


    }
}
