package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase Repository para la entidad Ubicacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface UbicacionRepo extends JpaRepository<Ubicacion, Integer>{
}
