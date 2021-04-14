package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepo extends JpaRepository<Calificacion,Integer> {
}
