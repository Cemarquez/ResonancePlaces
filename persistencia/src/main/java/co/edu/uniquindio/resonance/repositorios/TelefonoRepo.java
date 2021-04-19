package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase Repository para la entidad Telefono
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface TelefonoRepo extends JpaRepository<Telefono, Integer>{
}
