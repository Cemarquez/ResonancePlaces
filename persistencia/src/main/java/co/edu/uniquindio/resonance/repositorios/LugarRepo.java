package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.*;
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



    //@Query("select new co.edu.uniquindio.resonance.repositorios.Consulta7DTO(l.ciudad, count(l.ciudad) ) from Lugar l where l.estado = false group by l.ciudad ")
    @Query("select l.ciudad.nombre, count(l.ciudad) from Lugar l where l.estado = false group by l.ciudad")
    List<Object[]> devolverCantidadPorCiudad();



    @Query("select count(l), t.nombre from Categoria t inner join t.lugar l inner join l.horarios h where h.horaInicio<:hora and h.horaCierre>:hora group by l.categoria")
    List<Object[]> devolverAbiertosPorCat(Date hora);


    @Query("select c.nombre, avg(ca.valor) as prom from Categoria c INNER JOIN c.lugar l INNER JOIN l.calificaciones ca where l.ciudad.nombre = ?1  group by l.categoria order by(prom) DESC " )
    List<Object[]> devolverCategoriaPromMayor(String ciudad);

    @Query("select l from Lugar  l where l.nombre like concat('%', :parametro, '%') and l.rechazado = false AND l.estado = true")
    List<Lugar> buscarLugares(String parametro);

    @Query("select c from Calificacion c  where c.lugar.codigo =?1 ")
    List<Calificacion> listarCalificaciones(Integer parametro);

    @Query("select h from Horario h  where h.lugar.codigo =?1 ")
    List<Horario> listarHorarios(Integer parametro);

    @Query("select f from Favorito f where f.lugar.codigo = ?2 and f.usuario.nickname = ?1")
    Favorito obtenerFavorito(String nicknameUsuario, Integer codigoLugar);

    @Query("SELECT l FROM Lugar l WHERE l.rechazado = false AND l.estado = true")
    List<Lugar> listarLugaresAutorizados();

    @Query("SELECT f from Lugar l INNER JOIN Favorito f ON f.lugar.codigo = l.codigo WHERE f.lugar.codigo = ?1")
        List<Favorito> obtenerFavoritos(Integer codigoLugar);

    @Query("SELECT c from Lugar l INNER JOIN Calificacion c ON c.lugar.codigo = l.codigo WHERE c.lugar.codigo =?1")
    List<Calificacion> obtenerCalificaciones(Integer codigoLugar);

    @Query("SELECT h from Lugar l INNER JOIN Horario h ON l.codigo = h.lugar.codigo WHERE h.lugar.codigo = ?1")
    List<Horario> obtenerHorarios(Integer codigoLugar);

}
