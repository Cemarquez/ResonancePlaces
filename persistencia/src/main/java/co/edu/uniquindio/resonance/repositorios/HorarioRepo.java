package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Clase Repositorio para la entidad Horario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface HorarioRepo extends JpaRepository<Horario,Integer> {
}
