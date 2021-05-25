package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Ubicacion;
import co.edu.uniquindio.resonance.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
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

        Categoria buscado = categoriaRepo.findById(guardada.getCodigo()).orElse(null);
        Assertions.assertEquals("Parqueadero", buscado.getNombre());

    }

    /**
     * Método que permite listar los lugares con una categoria determinada en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql", "classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql", "classpath:lugares.sql"})
    public void listarLugares(){
        List<Lugar> lugares = categoriaRepo.obtenerLugares(1);

        for(Lugar l : lugares){
            System.out.println(l.getNombre());
        }
    }

    /**
     * Método que permite obtener una categoria con el nombre en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql"})
    public void obtenerCategoriaNombre(){
        Categoria categoria = categoriaRepo.findByNombre("Restaurante");

        System.out.println(categoria.getDescripcion());
    }

    /**
     * Método que permite obtener una categoria con el codigo en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql"})
    public void obtenerCategoriaCodigo(){
        Categoria categoria = categoriaRepo.findByCodigo(1);

        System.out.println(categoria.getDescripcion());
    }

    /**
     * Método que permite obtener todas las categorias en la base de datos con paginado en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:categorias.sql")
    public void listarCategoriasPaginadosTest(){

        List<Categoria> lista = categoriaRepo.obtenerCategorias(PageRequest.of(0,3));


        for(Categoria c: lista) {
            System.out.println(c.getNombre());
        }

    }

    /**
     * Método que permite obtener todas las categorias ordenadas alfabeticamente en la base de datos con paginado en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql("classpath:categorias.sql")
    public void listarCategoriasOrdenadasTest(){

        List<Categoria> lista = categoriaRepo.obtenerListaCategoriasAlfabeticamente();


        for(Categoria c: lista) {
            System.out.println(c.getNombre());
        }

    }
}
