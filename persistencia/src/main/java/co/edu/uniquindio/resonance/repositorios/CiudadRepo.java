package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.entidades.Lugar;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad Ciudad
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface CiudadRepo extends JpaRepository<Ciudad,Integer> {

    @Query("select c from Ciudad  c where c.nombre = ?1")
    Ciudad buscarPorNombre(String nombre);

    @Query("select l from Lugar l where l.ciudad.codigo = ?1")
    List<Lugar> obtenerLugares(Integer codigo);
}
