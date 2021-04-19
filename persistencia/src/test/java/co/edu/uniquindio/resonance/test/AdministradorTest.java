package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;


/**
 * Clase Test para la entidad administrador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    /**
     * Repositorio de la entidad administrador
     */
    @Autowired
    public AdministradorRepo adminRepo;

    /**
     * Método que permite registrar un administrador en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarAdministradorTest(){
        Administrador admin = new Administrador();
        admin.setNickname("JuanitoMaster");
        admin.setEmail("juanitom@gmail.com");
        admin.setNombre("Juanito");
        admin.setContrasena("juanito1234");

        Administrador guardado = adminRepo.save(admin);
        Assertions.assertNotNull(guardado);
    }

    /**
     * Método que permite eliminar un administrador de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarAdminTest(){
        Administrador admin = new Administrador();
        admin.setNickname("JuanitoMaster");
        admin.setEmail("juanitom@gmail.com");
        admin.setNombre("Juanito");
        admin.setContrasena("juanito1234");

        Administrador guardado = adminRepo.save(admin);

        adminRepo.delete(guardado);

        Administrador buscado = adminRepo.findById("JuanitoMaster").orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * Método que permite listar todos los registros de administradores de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:administradores.sql")
    public void listarAdminsTest(){
        List<Administrador> lista = adminRepo.findAll();

        System.out.println(lista.get(0).getNombre());
    }


    /**
     * Método que permite actualizar los datos correspondientes de un administrador de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarAdminTest(){
        Administrador admin = new Administrador();
        admin.setNickname("JuanitoMaster");
        admin.setEmail("juanitom@gmail.com");
        admin.setNombre("Juanito");
        admin.setContrasena("juanito1234");

        Administrador guardado = adminRepo.save(admin);

        guardado.setNombre("Fulano");
        adminRepo.save(guardado);

        Administrador buscado = adminRepo.findById("JuanitoMaster").orElse(null);
        Assertions.assertEquals("Fulano", buscado.getNombre());

    }

}
