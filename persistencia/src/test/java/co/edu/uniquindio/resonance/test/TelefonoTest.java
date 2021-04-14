package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Telefono;
import co.edu.uniquindio.resonance.repositorios.TelefonoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelefonoTest {

    @Autowired
    public TelefonoRepo telefonoRepo;


    @Test
    public void registrarTelefonoTest(){
        Telefono tele = new Telefono();
        tele.setCodigo(1)
        tele.setNumero("3106947999");

        Telefono guardado = telefonoRepo.save(tele);
        Assertions.assertNotNull(guardado);
    }

    @Test
    public void eliminarTelefonoTest(){
        Telefono tele = new Telefono();
        tele.setCodigo(1)
        tele.setNumero("3106947999");

        Telefono guardado = telefonoRepo.save(tele);

        telefonoRepo.delete(guardado);

        Telefono buscado = telefonoRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void listarTelefonoTest(){
        List<Telefono> lista = telefonoRepo.findAll();

        System.out.println(lista);
    }


    @Test
    public void actualizarTelefonoTest(){
        Telefono tele = new Telefono();
        tele.setCodigo(1)
        tele.setNumero("3106947999");

        Telefono guardado = telefonoRepo.save(tele);

        guardado.setNumero("3148934012");
        telefonoRepo.save(guardado);

        Telefono buscado = telefonoRepo.findById(1).orElse(null);
        Assertions.assertEquals("3148934012", buscado.getNumero());

    }

}
