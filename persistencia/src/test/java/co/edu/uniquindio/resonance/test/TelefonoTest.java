package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Telefono;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.repositorios.TelefonoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TelefonoTest {

    @Autowired
    public TelefonoRepo telefonoRepo;
    @Autowired
    private LugarRepo lugarRepo;


    @Test
    public void registrarTelefonoTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Telefono tele = new Telefono();
        tele.setCodigo(1);
        tele.setNumero("3106947999");
        tele.setLugar(lugarGuardado);

        Telefono guardado = telefonoRepo.save(tele);
        Assertions.assertNotNull(guardado);
    }

    @Test
    public void eliminarTelefonoTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Telefono tele = new Telefono();
        tele.setCodigo(1);
        tele.setNumero("3106947999");
        tele.setLugar(lugarGuardado);

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
    @Sql({"classpath:categorias.sql", "classpath:usuarios.sql", "classpath:ubicaciones.sql", "classpath:lugares.sql", "classpath:telefonos.sql"})
    public void listarTelefonoSQL(){
        List<Telefono> lista = telefonoRepo.findAll();

        System.out.println(lista);
    }


    @Test
    public void actualizarTelefonoTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        Telefono tele = new Telefono();
        tele.setCodigo(1);
        tele.setNumero("3106947999");
        tele.setLugar(lugarGuardado);

        Telefono guardado = telefonoRepo.save(tele);

        guardado.setNumero("3148934012");
        telefonoRepo.save(guardado);

        Telefono buscado = telefonoRepo.findById(1).orElse(null);
        Assertions.assertEquals("3148934012", buscado.getNumero());

    }

}
