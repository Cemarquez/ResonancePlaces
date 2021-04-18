package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo cRepo;

    @Test
    public void registrarCiudad(){
        Ciudad c = new Ciudad();
        c.setCodigo(1);
        c.setNombre("Armenia");

        Ciudad cGuardada = cRepo.save(c);
        Assertions.assertNotNull(cGuardada);
    }

    @Test
    public void eliminarCiudad(){
        Ciudad c = new Ciudad();
        c.setCodigo(1);
        c.setNombre("Armenia");

        Ciudad cGuardada = cRepo.save(c);

        cRepo.delete(c);
        Ciudad buscada = cRepo.findById("1").orElse(null);
        Assertions.assertNotNull(buscada);
    }

    @Test
    public void actualizarCiudad(){
        Ciudad c = new Ciudad();
        c.setCodigo(1);
        c.setNombre("Armenia");

        Ciudad cGuardada = cRepo.save(c);

        c.setNombre("Calarca");
        cRepo.save(c);
        Ciudad buscada = cRepo.findById("1").orElse(null);
        Assertions.assertEquals("Calarca", buscada.getNombre());

    }

    @Test
    @Sql("classpath:ciudades.sql")
    public void listarCiudades(){
        List<Ciudad> list = cRepo.findAll();
        for (Ciudad c : list){
            System.out.println(c.getNombre());
        }
    }
}
