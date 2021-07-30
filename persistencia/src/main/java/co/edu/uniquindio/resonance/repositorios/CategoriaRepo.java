package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad Categoria
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    /**
     * Query que permite obtener los lugares de una categoria determinada
     * @param codigo
     * @return
     */
    @Query("select l from Categoria c, IN(c.lugar) l where c.codigo = ?1 and l.estado = true and l.rechazado =false")
    List<Lugar> obtenerLugares(int codigo);

    /**
     * Query que permite obtener una categoria acorde a un nombre determinado
     * @param nombre
     * @return
     */
    Categoria findByNombre(String nombre);

    /**
     * Query que permite obtener una categoria acorde a un codigo determinado
     * @param codigo
     * @return
     */
    Categoria findByCodigo(int codigo);

    /**
     * Query que permite obtener todas las categorias usando paginado
     * @param pageable
     * @return
     */
    @Query("select c from Categoria c")
    List<Categoria> obtenerCategorias(PageRequest pageable);

    /**
     * Query que permite obtener las categorias en orden alfabético
     * @return
     */
    @Query("select c from Categoria c order by c.nombre ASC")
    List<Categoria> obtenerListaCategoriasAlfabeticamente();
}
