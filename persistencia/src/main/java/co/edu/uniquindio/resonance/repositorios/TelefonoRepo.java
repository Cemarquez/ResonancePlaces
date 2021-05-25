package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Telefono;
import co.edu.uniquindio.resonance.entidades.Ubicacion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad Telefono
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface TelefonoRepo extends JpaRepository<Telefono, Integer>{

    /**
     * Query que permite obtener un telefono acorde a un codigo determinado
     * @param codigo
     * @return
     */
    Telefono findByCodigo(int codigo);

    /**
     * Query que permite obtener todos los telefonos de un lugar determinado
     * @param codigo
     * @return
     */
    @Query("select t from Telefono t where t.lugar.codigo = ?1")
    List<Telefono> telefonosLugar(int codigo);

    /**
     * Query que permite obtener todos los telefonos usando paginado
     * @param pageable
     * @return
     */
    @Query("select t from Telefono t")
    List<Telefono> obtenerTelefonos(PageRequest pageable);
}
