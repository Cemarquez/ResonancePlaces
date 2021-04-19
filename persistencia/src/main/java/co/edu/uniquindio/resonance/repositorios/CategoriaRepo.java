package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase Repository para la entidad Categoria
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
