package co.edu.uniquindio.resonance.repositorios;


import co.edu.uniquindio.resonance.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Clase Repositorio para la entidad Usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface UsuarioRepo  extends JpaRepository<Usuario,String> {


}
