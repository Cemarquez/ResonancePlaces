package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.repositorios.AdministradorRepo;
import co.edu.uniquindio.resonance.repositorios.ModeradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Clase Test para la entidad moderador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModeradorTest {

    /**
     * Repositorio de la entidad moderador
     */
    @Autowired
    private ModeradorRepo moderadorRepo;

    /**
     * Repositorio de la entidad administrador
     */
    @Autowired
    private AdministradorRepo adminitradorRepo;

    /**
     * Método que permite registrar un moderador en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarModerador(){
        Administrador admin = new Administrador();
        admin.setNickname("JuanitoMaster");
        admin.setEmail("juanitom@gmail.com");
        admin.setNombre("Juanito");
        admin.setContrasena("juanito1234");

        Administrador guardado = adminitradorRepo.save(admin);

        Moderador mod = new Moderador("gonzalesM", "Mateo Gonzales", "mateo@gmail.com", "mateito1234");
        mod.setAdministrador(admin);
        Moderador modGuardado = moderadorRepo.save(mod);
        Assertions.assertNotNull(modGuardado);
    }

    /**
     * Método que permite eliminar un moderador de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarModerador(){
        Moderador mod = new Moderador("gonzalesM", "Mateo Gonzales", "mateo@gmail.com", "mateito1234");
        Moderador modGuardado = moderadorRepo.save(mod);

        moderadorRepo.delete(mod);

        Moderador buscado = moderadorRepo.findById("gonzalesM").orElse(null);
        Assertions.assertNotNull(buscado);
    }

    /**
     * Método que permite actualizar los datos correspondientes de un moderador de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarModerador(){
        Moderador mod = new Moderador("gonzalesM", "Mateo Gonzales", "mateo@gmail.com", "mateito1234");
        Moderador modGuardado = moderadorRepo.save(mod);
        mod.setNombre("Mateo Castañeda");
        moderadorRepo.save(mod);
        Moderador buscado = moderadorRepo.findById("gonzalesM").orElse(null);
        Assertions.assertEquals("Mateo Castañeda", buscado.getNombre());
    }

    /**
     * Método que permite listar todos los registros de moderadores de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:administradores.sql","classpath:moderadores.sql"})
    public void listarModeradores(){
        List<Moderador> list = moderadorRepo.findAll();
        for(Moderador m : list){
            System.out.println(m.getNickname());
        }
    }
}
