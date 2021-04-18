package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepo extends JpaRepository<Reporte,String> {
}
