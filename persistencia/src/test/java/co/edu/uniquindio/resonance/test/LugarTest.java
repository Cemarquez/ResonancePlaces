package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    @Autowired
    public LugarRepo lugarRepo;


    @Test
    public void registrarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar guardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(guardado);
    }

    @Test
    public void eliminarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar guardado = lugarRepo.save(lugar);

        lugarRepo.delete(guardado);

        Lugar buscado = lugarRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void listarLugarTest(){
        List<Lugar> lista = lugarRepo.findAll();

        System.out.println(lista);
    }


    @Test
    public void actualizarLugarTest(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar guardado = lugarRepo.save(lugar);

        guardado.setNombre("Fulanoburger");
        lugarRepo.save(guardado);

        Lugar buscado = lugarRepo.findById(1).orElse(null);
        Assertions.assertEquals("Fulanoburger", buscado.getNombre());

    }

}
