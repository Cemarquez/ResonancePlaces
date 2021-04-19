package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Reporte;
import co.edu.uniquindio.resonance.repositorios.ReporteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;

/**
 * Clase Test para la entidad reporte
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReporteTest {

    /**
     * Repositorio de la entidad reporte
     */
    @Autowired
    private ReporteRepo rRepo;


    /**
     * Método que permite registrar un reporte en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarReporte(){
        Reporte r = new Reporte(1, new Date(), "Reporte mantenimiento", "Reporte 1");

        Reporte guardado = rRepo.save(r);
        Assertions.assertNotNull(guardado);

    }

    /**
     * Método que permite eliminar un reporte de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void EliminarReporte(){
        Reporte r = new Reporte(1, new Date(), "Reporte mantenimiento", "Reporte 1");

        Reporte guardado = rRepo.save(r);

        rRepo.delete(r);
        Reporte buscado = rRepo.findById(guardado.getCodigo()).orElse(null);
        Assertions.assertNotNull(buscado);
    }

    /**
     * Método que permite actualizar los datos correspondientes de un reporte de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarReporte(){
        Reporte r = new Reporte(1, new Date(), "Reporte mantenimiento", "Reporte 1");

        Reporte guardado = rRepo.save(r);

        r.setNombre("R 1");
        rRepo.save(r);
        Reporte buscado = rRepo.findById(guardado.getCodigo()).orElse(null);
        Assertions.assertEquals("R 1", buscado.getNombre());
    }

    /**
     * Método que permite listar todos los registros de los reportes de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:reportes.sql")
    public void listarReportes(){
        List<Reporte> list = rRepo.findAll();
        for (Reporte r : list){
            System.out.println(r.getNombre());
        }
    }
}
