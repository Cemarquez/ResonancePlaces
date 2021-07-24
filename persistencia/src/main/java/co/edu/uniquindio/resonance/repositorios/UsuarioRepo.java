package co.edu.uniquindio.resonance.repositorios;


import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Usuario;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Clase Repositorio para la entidad Usuario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface UsuarioRepo  extends JpaRepository<Usuario,String> {


    /**
     * Query que permite obtener los lugares de un usuario determinado
     * @param nickname
     * @return
     */
    @Query("select l from Usuario u, IN(u.lugares) l where u.nickname = ?1")
    List<Lugar> obtenerLugares(String nickname);

    /**
     * Query que permite obtener los lugares autorizados de un usuario determinado
     * @param nickname
     * @return
     */
    @Query("select l from Usuario u, IN(u.lugares) l where u.nickname = ?1 and l.estado = true")
    List<Lugar> obtenerLugaresAutorizados(String nickname);

    /**
     * Query que permite obtener los lugares no autorizados de un usuario determinado
     * @param nickname
     * @return
     */
    @Query("select l from Usuario u, IN(u.lugares) l where u.nickname = ?1 and l.estado = false")
    List<Lugar> obtenerLugaresNoAutorizados(String nickname);

    /**
     * Query que permite obtener los lugares favoritos de un usuario determinado
     * @param nickname
     * @return
     */
    @Query("select l.lugar from Usuario u JOIN u.favoritos l where u.nickname = ?1")
    List<Lugar> obtenerLugaresFavoritos(String nickname);

    /**
     * Query que permite obtener todos los usuarios usando paginado
     * @param pageable
     * @return
     */
    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(PageRequest pageable);

    /**
     * Query que permite obtener un usuario acorde a un email determinado
     * @param email
     * @return
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Query que permite obtener un usuario acorde a un nickname determinado
     * @param nickname
     * @return
     */
    Usuario findByNickname(String nickname);

    /**
     * Query que permite obtener un usuario determinado acorde a un nickname y contrasena
     * @param nickname
     * @param contrasena
     * @return
     */
    Usuario findByNicknameAndContrasena(String nickname,String contrasena);


    /**
     * Query que permite obtener un usuario determinado acorde a un email y contrasena
     * @param email
     * @param contrasena
     * @return
     */
    Usuario findByEmailAndContrasena(String email, String contrasena);

    /**
     * Query que permite obtener el email y los lugares publicados de todos los usuarios (asi no tengan lugares publicados)
     * @return
     */
    @Query("select u.email, l from Usuario u LEFT JOIN u.lugares l ON u.email = l.usuario.email")
    List<Object[]> obtenerEmailLugaresPublicados();

    /**
     * Query que permite obtener los usuarios en orden alfabético
     * @return
     */
    @Query("select u from Usuario u order by u.nombre ASC")
    List<Usuario> obtenerListaUsuariosAlfabeticamente();

    /**
     * Query que permite obtener los usuarios con correo de Gmail
     * @return
     */
    @Query("select  u from Usuario u where u.email like '%@gmail.%' ")
    List<Usuario> obtenerUsuariosDeGmail();

    /**
     * Query que permite obtener los usuarios con correo dado
     * @return
     */
    @Query("select  u from Usuario u where u.email like  concat('%',?1,'%')  ")
    List<Usuario> obtenerUsuariosDeDominio(String dominio);

}
