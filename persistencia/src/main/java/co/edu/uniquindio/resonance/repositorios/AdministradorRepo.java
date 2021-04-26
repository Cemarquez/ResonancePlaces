package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad administrador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {


    @Query("select ad from Administrador ad")
    List<Administrador> listarAdministradores();






}
