package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepo extends JpaRepository<Foto,String> {
}
