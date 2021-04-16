package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Ubicacion;
import co.edu.uniquindio.resonance.repositorios.UbicacionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UbicacionTest {

    @Autowired
    public UbicacionRepo ubicacionRepo;


    @Test
    public void registrarUbicacionTest(){
        Ubicacion ubi = new Ubicacion();
        ubi.setCodigo(1);
        ubi.setLatitud(3.5);
        ubi.setLongitud(3.5);

        Ubicacion guardado = ubicacionRepo.save(ubi);
        Assertions.assertNotNull(guardado);
    }

    @Test
    public void eliminarUbicacionTest(){
        Ubicacion ubi = new Ubicacion();
        ubi.setCodigo(1);
        ubi.setLatitud(3.5);
        ubi.setLongitud(3.5);

        Ubicacion guardado = ubicacionRepo.save(ubi);

        ubicacionRepo.delete(guardado);

        Ubicacion buscado = ubicacionRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void listarUbicacionTest(){
        List<Ubicacion> lista = ubicacionRepo.findAll();

        System.out.println(lista);
    }


    @Test
    public void actualizarTelefonoTest(){
        Ubicacion ubi = new Ubicacion();
        ubi.setCodigo(1);
        ubi.setLatitud(3.5);
        ubi.setLongitud(3.5);

        Ubicacion guardado = ubicacionRepo.save(ubi);

        guardado.setLongitud(6.5);
        ubicacionRepo.save(guardado);

        Ubicacion buscado = ubicacionRepo.findById(1).orElse(null);
        Assertions.assertEquals(6.5, buscado.getLongitud());
    }

}
