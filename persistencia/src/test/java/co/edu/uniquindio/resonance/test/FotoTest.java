package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Foto;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.FotoRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FotoTest {

    @Autowired
    private FotoRepo repo;

    @Autowired
    private LugarRepo lugarRepo;

    @Test
    public void registrarFoto(){
        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar guardado = lugarRepo.save(lugar);

        Foto f = new Foto(1, lugar);

        Foto guardada = repo.save(f);
        Assertions.assertNotNull(guardado);

    }

    @Test
    public void actualizarFoto(){
        Foto f = new Foto();
        f.setCodigo(1);
        Foto guardada = repo.save(f);
        f.setCodigo(2);
        repo.save(f);

        Foto buscado = repo.findById("2").orElse(null);
        Assertions.assertEquals("2", buscado.getCodigo());
    }

    @Test
    public void eliminarFoto(){
        Foto f = new Foto();
        f.setCodigo(1);
        Foto guardada = repo.save(f);
        repo.delete(f);
        Foto buscado = repo.findById("1").orElse(null);
        Assertions.assertNotNull(buscado);
    }

    @Test
    @Sql("classpath:fotos.sql")
    public void listarFotos(){
        List<Foto> list = repo.findAll();
        for(Foto f : list){
            System.out.println(f.getUrlFoto());
        }
    }
}
