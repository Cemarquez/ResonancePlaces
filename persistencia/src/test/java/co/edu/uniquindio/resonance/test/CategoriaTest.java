package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    public CategoriaRepo categoriaRepo;


    @Test
    public void registrarCategoriaTest(){
        Categoria cate = new Categoria();
        cate.setCodigo(1)
        cate.setNombre("Restaurante");
        cate.setDescripcion("Mejor lugar de comidas rapidas");

        Categoria guardada = categoriaRepo.save(cate);
        Assertions.assertNotNull(guardada);
    }

    @Test
    public void eliminarCategoriaTest(){
        Categoria cate = new Categoria();
        cate.setCodigo(1)
        cate.setNombre("Restaurante");
        cate.setDescripcion("Mejor lugar de comidas rapidas");

        Categoria guardada = categoriaRepo.save(cate);

        categoriaRepo.delete(guardada);

        Categoria buscado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void listarCategoriaTest(){
        List<Categoria> lista = categoriaRepo.findAll();

        System.out.println(lista);
    }


    @Test
    public void actualizarCategoriaTest(){
        Categoria cate = new Categoria();
        cate.setCodigo(1)
        cate.setNombre("Restaurante");
        cate.setDescripcion("Lugar donde las personas comen");

        Categoria guardada = categoriaRepo.save(cate);;

        guardado.setNombre("Parqueadero");
        categoriaRepo.save(guardado);

        Categoria buscado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertEquals("Parqueadero", buscado.getNombre());

    }

}
