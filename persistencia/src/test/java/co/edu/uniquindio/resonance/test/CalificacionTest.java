package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.repositorios.CalificacionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CalificacionTest {

    @Autowired
    private CalificacionRepo calificacionRepo;


    @Test
    public void registrarCalificacion(){

        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular");
        Calificacion calificacionGuardada = calificacionRepo.save(calificacion);

        Assertions.assertNotNull(calificacionGuardada);


    }

    @Test
    public void eliminarCalificacion(){
        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular");
        Calificacion calificacionGuardada = calificacionRepo.save(calificacion);
        calificacionRepo.delete(calificacionGuardada);

        Calificacion buscado = calificacionRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);


    }

    @Test
    public void actualizarCalificacion(){
        Calificacion calificacion = new Calificacion(3.0,"Excelente lugar","Buena atencion pero la comida muy regular");
        Calificacion calificacionGuardada = calificacionRepo.save(calificacion);

        calificacionGuardada.setTitulo("Paisaje espectacular");

        calificacionRepo.save(calificacionGuardada);
        Calificacion buscado = calificacionRepo.findById(1).orElse(null);

        Assertions.assertEquals("Paisaje espectacular",calificacionGuardada.getTitulo());


    }

    @Test
    public void listarCalificaciones(){
        List<Calificacion> lista = calificacionRepo.findAll();
        System.out.println(lista);

    }



}
