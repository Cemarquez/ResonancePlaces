package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase Repository para la entidad Foto
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface FotoRepo extends JpaRepository<Foto,Integer> {
}
