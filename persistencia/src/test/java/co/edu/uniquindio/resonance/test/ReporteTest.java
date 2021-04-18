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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReporteTest {

    @Autowired
    private ReporteRepo rRepo;

    @Test
    public void registrarReporte(){
        Reporte r = new Reporte(1, new Date(), "Reporte mantenimiento", "Reporte 1");

        Reporte guardado = rRepo.save(r);
        Assertions.assertNotNull(guardado);

    }

    @Test
    public void EliminarReporte(){
        Reporte r = new Reporte(1, new Date(), "Reporte mantenimiento", "Reporte 1");

        Reporte guardado = rRepo.save(r);

        rRepo.delete(r);
        Reporte buscado = rRepo.findById("1").orElse(null);
        Assertions.assertNotNull(buscado);
    }

    @Test
    public void actualizarReporte(){
        Reporte r = new Reporte(1, new Date(), "Reporte mantenimiento", "Reporte 1");

        Reporte guardado = rRepo.save(r);

        r.setNombre("R 1");
        rRepo.save(r);
        Reporte buscado = rRepo.findById("1").orElse(null);
        Assertions.assertEquals("R 1", buscado.getNombre());
    }

    @Test
    @Sql("classpath:reportes.sql")
    public void listarReportes(){
        List<Reporte> list = rRepo.findAll();
        for (Reporte r : list){
            System.out.println(r.getNombre());
        }
    }
}
