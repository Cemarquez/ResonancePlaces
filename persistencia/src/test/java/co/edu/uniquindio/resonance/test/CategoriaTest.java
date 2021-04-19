package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Clase Test para la entidad categoria
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    /**
     * Repositorio de la entidad categoria
     */
    @Autowired
    public CategoriaRepo categoriaRepo;

    /**
     * Método que permite registrar una categoria en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarCategoriaTest(){
        Categoria cate = new Categoria();
        cate.setCodigo(1);
        cate.setNombre("Restaurante");
        cate.setDescripcion("Mejor lugar de comidas rapidas");

        Categoria guardada = categoriaRepo.save(cate);
        Assertions.assertNotNull(guardada);
    }

    /**
     * Método que permite eliminar una categoria de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarCategoriaTest(){
        Categoria cate = new Categoria();
        cate.setCodigo(1);
        cate.setNombre("Restaurante");
        cate.setDescripcion("Mejor lugar de comidas rapidas");

        Categoria guardada = categoriaRepo.save(cate);

        categoriaRepo.delete(guardada);

        Categoria buscado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    /**
     * Método que permite listar todos los registros de categorias de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql"})
    public void listarCategoriaTestSQL(){
        List<Categoria> lista = categoriaRepo.findAll();

        System.out.println(lista);
    }

    /**
     * Método que permite actualizar los datos correspondientes de una categoria de la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarCategoriaTest(){
        Categoria cate = new Categoria();
        cate.setCodigo(1);
        cate.setNombre("Restaurante");
        cate.setDescripcion("Lugar donde las personas comen");

        Categoria guardada = categoriaRepo.save(cate);

        guardada.setNombre("Parqueadero");
        categoriaRepo.save(guardada);

        Categoria buscado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertEquals("Parqueadero", buscado.getNombre());

    }

}
