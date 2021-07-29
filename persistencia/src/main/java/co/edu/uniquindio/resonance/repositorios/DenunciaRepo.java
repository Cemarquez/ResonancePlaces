package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DenunciaRepo extends JpaRepository<Denuncia, Integer> {

    @Query("select d from Denuncia d where d.aprobado=false")
    List<Denuncia> obtenerDenunciasSinAprobar();



}
