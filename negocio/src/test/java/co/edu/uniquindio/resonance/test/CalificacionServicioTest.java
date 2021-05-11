package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.NegocioApplication;
import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.CalificacionRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import co.edu.uniquindio.resonance.servicios.CalificacionServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase Test para CalificacionServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CalificacionServicioTest {

    /**
     * Servicio de entidad Calificacion
     */
    @Autowired
    private CalificacionServicio calificacionServicio;

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
     * Repositorio de la entidad usuario
     */
    @Autowired
    private UsuarioRepo usuarioRepo;


    /**
     * Método que permite registrar una calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarCalificacionTest(){


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


        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular",usuarioGuardado,lugarGuardado);
        Calificacion calificacionGuardada = null;
        try {
            calificacionGuardada = calificacionServicio.registrarCalificacion(calificacion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(calificacionGuardada);


    }

    /**
     * Método que permite eliminar una calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarCalificacionTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);


        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);



        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular",usuarioGuardado,lugarGuardado);

        try {
            Calificacion calificacionGuardada = calificacionServicio.registrarCalificacion(calificacion);
            calificacionServicio.eliminarCalificacion(calificacionGuardada);
            Calificacion buscado = calificacionRepo.findById(calificacionGuardada.getCodigo()).orElse(null);

            Assertions.assertNull(buscado);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    /**
     * Método que permite actualizar una calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarCalificacionTest(){

        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);
        Lugar lugarGuardado = lugarRepo.save(lugar);


        Usuario usuario = new Usuario("PedroNavaja", "Pedro", "pedronavaja@gmail.com", "pedro12345");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);



        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular",usuarioGuardado,lugarGuardado);
        Calificacion calificacionGuardada = null;
        try {
            calificacionGuardada = calificacionServicio.registrarCalificacion(calificacion);
            calificacionGuardada.setTitulo("Paisaje espectacular");

            calificacionServicio.actualizarCalificacion(calificacionGuardada);
            Calificacion buscado = calificacionRepo.findById(calificacionGuardada.getCodigo()).orElse(null);

            Assertions.assertEquals("Paisaje espectacular",calificacionGuardada.getTitulo());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que permite listar calificacion en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void listarCalificacionesTest(){
        List<Calificacion> lista = calificacionServicio.listarCalificaciones();
        System.out.println(lista);

    }

    /**
     * Método que lista calificaciones en la base de datos segun valor dado en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void obtenerListaPorCalificacionTest(){

        List<Calificacion> lista = calificacionServicio.obtenerListaPorCalificacion(1.0);

        for(Calificacion c: lista){
            System.out.println(c.getTitulo() + " valor: " + c.getValor() );
        }

    }


}
