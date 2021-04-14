package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepo extends JpaRepository<Favorito,Integer> {
}
