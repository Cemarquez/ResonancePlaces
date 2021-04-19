package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Clase Test para la entidad lugar
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    /**
     * Repositorio de la entidad lugar
     */
    @Autowired
    public LugarRepo lugarRepo;

    /**
     * Repositorio de la entidad ubicacion
     */
    @Autowired
    public UbicacionRepo ubicacionRepo;

    /**
     * Repositorio de la entidad categoria
     */
    @Autowired
    public CategoriaRepo categoriaRepo;

    /**
     * Repositorio de la entidad usuario
     */
    @Autowired
    public UsuarioRepo usuarioRepo;

    /**
     * Repositorio de la entidad moderador
     */
    @Autowired
    public ModeradorRepo moderadorRepo;

    /**
     * Repositorio de la entidad ciudad
     */
    @Autowired
    public CiudadRepo ciudadRepo;

    /**
     * Método que permite registrar un lugar en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql"})
    public void registrarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        lugar.setCategoria(categoriaRepo.findAll().get(0));
        lugar.setUbicacion(ubicacionRepo.findAll().get(0));
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        lugar.setCiudad(ciudadRepo.findAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));

        Lugar guardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(guardado);
    }

    /**
     * Método que permite eliminar un lugar de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql"})
    public void eliminarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        lugar.setCategoria(categoriaRepo.findAll().get(0));
        lugar.setUbicacion(ubicacionRepo.findAll().get(0));
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        lugar.setCiudad(ciudadRepo.findAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));

        Lugar guardado = lugarRepo.save(lugar);

        lugarRepo.delete(guardado);

        Lugar buscado = lugarRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * Método que permite listar todos los registros de lugar de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql"})
    public void listarLugarTest(){
        List<Lugar> lista = lugarRepo.findAll();

        for(Lugar l : lista){
            System.out.println(l.getNombre());
        }
    }

    /**
     * Método que permite actualizar los datos correspondientes de un lugar de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql"})
    public void actualizarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        lugar.setCategoria(categoriaRepo.findAll().get(0));
        lugar.setUbicacion(ubicacionRepo.findAll().get(0));
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        lugar.setCiudad(ciudadRepo.findAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));

        Lugar guardado = lugarRepo.save(lugar);

        guardado.setNombre("Fulanoburger");
        lugarRepo.save(guardado);

        Lugar buscado = lugarRepo.findById(1).orElse(null);
        Assertions.assertEquals("Fulanoburger", buscado.getNombre());
    }

}
