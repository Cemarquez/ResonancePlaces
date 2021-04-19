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

/**
 * Clase Test para la entidad foto
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FotoTest {

    /**
     * Repositorio de la entidad foto
     */
    @Autowired
    private FotoRepo repo;

    /**
     * Repositorio de la entidad lugar
     */
    @Autowired
    private LugarRepo lugarRepo;

    /**
     * Método que permite registrar una foto en la base de datos en forma de test para verificar su correcto funcionamiento
     */
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

    /**
     * Método que permite actualizar los datos correspondientes de una foto de la base de datos en forma de test para verificar su correcto funcionamiento
     */
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

    /**
     * Método que permite eliminar una foto de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarFoto(){
        Foto f = new Foto();
        f.setCodigo(1);
        Foto guardada = repo.save(f);
        repo.delete(f);
        Foto buscado = repo.findById("1").orElse(null);
        Assertions.assertNotNull(buscado);
    }

    /**
     * Método que permite listar todos los registros de las fotos de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:fotos.sql")
    public void listarFotos(){
        List<Foto> list = repo.findAll();
        for(Foto f : list){
            System.out.println(f.getUrlFoto());
        }
    }
}
