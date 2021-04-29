package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Clase Repository para la entidad Lugar
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer>{



    @Query("select l from  Lugar l  join  l.telefono t where t.numero = :numTelefono")
    Lugar obtenerLugarSegunTelefono(String numTelefono );

    //@Query("select new co.edu.uniquindio.resonance.repositorios.Consulta7DTO(l.ciudad, count(l.ciudad) ) from Lugar l where l.estado = false group by l.ciudad ")
    @Query("select l.ciudad.nombre, count(l.ciudad) from Lugar l where l.estado = false group by l.ciudad")
    List<Object[]> devolverCantidadPorCiudad();



    @Query("select count(l), t.nombre from Categoria t inner join t.lugar l inner join l.horarios h where h.horaInicio<:hora and h.horaCierre>:hora group by l.categoria")
    List<Object[]> devolverAbiertosPorCat(Date hora);


    @Query("select c.nombre, avg(ca.valor) as prom from Categoria c INNER JOIN c.lugar l INNER JOIN l.calificaciones ca where l.ciudad.nombre = ?1  group by l.categoria order by(prom) DESC " )
    List<Object[]> devolverCategoriaPromMayor(String ciudad);
}
