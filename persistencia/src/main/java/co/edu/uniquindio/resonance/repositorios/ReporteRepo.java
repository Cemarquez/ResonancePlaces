package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Clase Repository para la entidad Reporte
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface ReporteRepo extends JpaRepository<Reporte,Integer> {

    @Query("select r from Reporte r")
    List<Reporte> listarReportes();

    @Query("select r from Reporte r where r.fecha = ?1")
    Reporte buscarPorFecha(Date fecha);


}
