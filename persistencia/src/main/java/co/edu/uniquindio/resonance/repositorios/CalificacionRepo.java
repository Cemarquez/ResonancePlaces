package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para la entidad Calificacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface CalificacionRepo extends JpaRepository<Calificacion,Integer> {


    /**
     * Query que permite obtener un listado de calificaciones mayores a un valor determinado
     * @param calificacion
     * @return
     */
    @Query("select c from Calificacion c where c.valor >?1")
    List<Calificacion> obtenerListaPorCalificacion(double calificacion);

    /**
     * Query que permite obtener el listado de usuarios sin repeticiones que comentaron en un lugar determinado
     * @param codigo
     * @return
     */
    @Query("select distinct c.usuario from Calificacion c where c.lugar.codigo = ?1")
    List<Usuario> usuariosComentarios(Integer codigo);




}
