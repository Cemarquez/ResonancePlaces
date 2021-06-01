package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad Foto
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface FotoRepo extends JpaRepository<Foto,Integer> {

    @Query("select f from Foto f where f.lugar.codigo = ?1")
    List<Foto> obtenerPorCodigoLugar(Integer codigoLugar);
}
