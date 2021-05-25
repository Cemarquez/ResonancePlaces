package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Ubicacion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad Ubicacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface UbicacionRepo extends JpaRepository<Ubicacion, Integer>{

    /**
     * Query que permite obtener una ubicacion acorde a un codigo determinado
     * @param codigo
     * @return
     */
    Ubicacion findByCodigo(int codigo);

    /**
     * Query que permite obtener todas las ubicaciones usando paginado
     * @param pageable
     * @return
     */
    @Query("select u from Ubicacion u")
    List<Ubicacion> obtenerUbicaciones(PageRequest pageable);
}
