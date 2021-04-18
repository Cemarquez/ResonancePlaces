package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad,String> {
}
