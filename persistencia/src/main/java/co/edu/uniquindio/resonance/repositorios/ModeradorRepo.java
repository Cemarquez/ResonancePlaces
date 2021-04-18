package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador,String> {

}
