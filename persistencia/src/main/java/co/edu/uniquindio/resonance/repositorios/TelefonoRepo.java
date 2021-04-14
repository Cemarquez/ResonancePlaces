package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepo extends JpaRepository<Telefono, Integer>{
}
