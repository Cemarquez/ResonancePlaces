package co.edu.uniquindio.resonance.repositorios;


import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repositorio para la entidad Usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface UsuarioRepo  extends JpaRepository<Usuario,String> {



    @Query("select l from Usuario u, IN(u.lugares) l where u.nickname = ?1")
    List<Lugar> obtenerLugares(String nickname);


    @Query("select l.lugar from Usuario u JOIN u.favoritos l where u.nickname = ?1")
    List<Lugar> obtenerLugaresFavoritos(String nickname);




}
