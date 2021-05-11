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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.awt.print.Pageable;
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


    /**
     * Método que permite listar los lugares de un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql"})
    public void listar(){
    List<Lugar> lugares = usuarioRepo.obtenerLugares("PedroNavaja");

    for(Lugar l : lugares){
        System.out.println(l.getNombre());
    }
    }

    /**
     * Método que permite listar los lugares favoritos de un usuario en la base de datos en forma de test para verificar su correcto funcionamiento
     */

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql","classpath:lugares.sql" ,"classpath:telefonos.sql","classpath:favoritos.sql"})
    public void  obtenerLugaresFavoritosTest(){

        List <Lugar> favoritos = usuarioRepo.obtenerLugaresFavoritos("Miyagi");

        String t =  favoritos.get(0).getNombre();

        System.out.println("Favorito encontrado: " + t );


    }

    /**
     * Método que permite obtener un usuario en la base de datos segun un nickname y contrasena dadas en forma de test para verificar su correcto funcionamiento
     */

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerUsuarioNicknameContrasenaTest(){

        Usuario u = usuarioRepo.findByNicknameAndContrasena("Miyagi","miyagi1234");

        Assertions.assertNotNull(u);

        System.out.println(u.getNickname());

    }
    /**
     * Método que permite obtener un usuario en la base de datos segun un email y contrasena dadas en forma de test para verificar su correcto funcionamiento
     */

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerUsuarioEmailContrasenaTest(){

        Usuario u = usuarioRepo.findByEmailAndContrasena("miyagi@gmail.com","miyagi1234");

        Assertions.assertNotNull(u);

        System.out.println(u.getNickname());

    }

    /**
     * Método que permite obtener todos los usuarios en la base de datos con paginado en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPaginadosTest(){

        List<Usuario> lista = usuarioRepo.obtenerUsuarios(PageRequest.of(0,2));


        for(Usuario u: lista) {
            System.out.println(u.getNickname());
        }

    }

    /**
     * Método que permiete obtener email y lugares favoritos de todos los usuarios en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:calificaciones.sql"})
    public void listarEmailLugaresTest(){


        List<Object[]> lista = usuarioRepo.obtenerEmailLugaresPublicados();

        for (Object [] o: lista){

            for (int i=0; i<o.length;i++){

                        if (i==0) {
                            System.out.println("Email: " + o[i] + "\n");
                        }else{
                            System.out.println("Lugar: " + o[i] + "\n");
                        }





            }


        }

    }

    /**
     * Método que permite obtener usuarios que usan correo gmail en forma de test para verificar su funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:calificaciones.sql"})
    public void obtenerUsuariosGmailTest(){

        List <Usuario> lista = usuarioRepo.obtenerUsuariosDeGmail();

        for (Usuario u: lista){
            System.out.println("Usuario :" + u.getNickname() + " Correo: " + u.getEmail());
        }


    }

    /**
     * Método que permite obtener usuarios segun un dominio de correo indicado ej: @hotmail. , @uniquindio. , @yahoo. , @gmail. en forma de test para verificar su funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:calificaciones.sql"})
    public void obtenerUsuariosDominiolTest(){

        List <Usuario> lista = usuarioRepo.obtenerUsuariosDeDominio("@gmail.");

        for (Usuario u: lista){
            System.out.println("Usuario :" + u.getNickname() + " Correo: " + u.getEmail());
        }


    }
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:calificaciones.sql"})
    public void obtenerUsuarioNicknameTest(){

        Usuario user = usuarioRepo.findByNickname("Miyagi");

        System.out.println(user.getNickname());
    }


}
