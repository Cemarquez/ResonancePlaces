package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest {

    @Autowired
    public AdministradorRepo adminRepo;


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

    @Test
    public void listarAdminsTest(){
        List<Administrador> lista = adminRepo.findAll();

        System.out.println(lista);
    }


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
