package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        lugar.setCiudad(ciudadRepo.findAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));

        Lugar guardado = lugarRepo.save(lugar);

        lugarRepo.delete(guardado);

        Lugar buscado = lugarRepo.findById(guardado.getCodigo()).orElse(null);
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
        lugar.setUsuario(usuarioRepo.findAll().get(0));

        lugar.setCiudad(ciudadRepo.findAll().get(0));
        lugar.setModerador(moderadorRepo.findAll().get(0));

        Lugar guardado = lugarRepo.save(lugar);

        guardado.setNombre("Fulanoburger");
        lugarRepo.save(guardado);

        Lugar buscado = lugarRepo.findById(guardado.getCodigo()).orElse(null);
        Assertions.assertEquals("Fulanoburger", buscado.getNombre());
    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql"})
    public void testConsulta(){
        List<Object[]> lista = lugarRepo.devolverCantidadPorCiudad();

        for(Object[] c : lista){
            for( Object o : c){
                System.out.println(o);
            }
        }
    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql","classpath:lugares.sql" ,"classpath:telefonos.sql"})
    public void  obtenerLugarSegunTelefonoTest(){

        Lugar lugar = lugarRepo.obtenerLugarSegunTelefono("3106458892");

        String t =  lugar.getNombre();

        System.out.println("Lugar encontrado: " + t );

    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:horarios.sql"})
    public void testConsulta9() throws ParseException {


        String hora = "16:00";
        try {
            Date actual = new SimpleDateFormat("HH:mm").parse(hora);
            System.out.println(actual);
            List<Object[]> listadoLugares =lugarRepo.devolverAbiertosPorCat (actual);
            for(Object  lugar[]: listadoLugares){
                for(Object c : lugar){
                    System.out.println(c);
                }
            }

        }catch (ParseException e){
            e.printStackTrace();
        }


    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:calificaciones.sql"})
    public void testConsulta10(){
        List<Object[]> lista = lugarRepo.devolverCategoriaPromMayor("Barrancabermeja");

        for(Object[] c : lista){
            for( Object o : c){
                System.out.println(o);
            }
        }
    }

    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql", "classpath:calificaciones.sql"})
    public void testBuscar(){
        List<Lugar> lista = lugarRepo.buscarLugares("%de%");
        for (Lugar l : lista){
            System.out.println(l.getNombre());
        }

    }

}
