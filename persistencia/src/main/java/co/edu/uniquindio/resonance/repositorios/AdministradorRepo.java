package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {

}
