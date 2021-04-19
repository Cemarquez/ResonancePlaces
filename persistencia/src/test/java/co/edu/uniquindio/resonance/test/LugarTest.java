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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    @Autowired
    public LugarRepo lugarRepo;
    @Autowired
    public UbicacionRepo ubicacionRepo;
    @Autowired
    public CategoriaRepo categoriaRepo;
    @Autowired
    public UsuarioRepo usuarioRepo;
    /*
    @Autowired
    public ModeradorRepo moderadorRepo;
    @Autowired
    public CiudadRepo ciudadRepo;
    */

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql"})
    public void registrarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        lugar.setCategoria(categoriaRepo.findAll().get(0));
        lugar.setUbicacion(ubicacionRepo.findAll().get(0));
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        /*
        lugar.setCiudad(ciudadRepo.finAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));
        */

        Lugar guardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(guardado);
    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql"})
    public void eliminarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        lugar.setCategoria(categoriaRepo.findAll().get(0));
        lugar.setUbicacion(ubicacionRepo.findAll().get(0));
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        /*
        lugar.setCiudad(ciudadRepo.finAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));
        */

        Lugar guardado = lugarRepo.save(lugar);

        lugarRepo.delete(guardado);

        Lugar buscado = lugarRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:lugares.sql"})
    public void listarLugarTest(){
        List<Lugar> lista = lugarRepo.findAll();

        System.out.println(lista);
    }


    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql"})
    public void actualizarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        lugar.setCategoria(categoriaRepo.findAll().get(0));
        lugar.setUbicacion(ubicacionRepo.findAll().get(0));
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        /*
        lugar.setCiudad(ciudadRepo.finAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));
        */

        Lugar guardado = lugarRepo.save(lugar);

        guardado.setNombre("Fulanoburger");
        lugarRepo.save(guardado);

        Lugar buscado = lugarRepo.findById(1).orElse(null);
        Assertions.assertEquals("Fulanoburger", buscado.getNombre());

    }

}
