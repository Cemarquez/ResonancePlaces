package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase Repository para la entidad Reporte
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface ReporteRepo extends JpaRepository<Reporte,Integer> {
}
