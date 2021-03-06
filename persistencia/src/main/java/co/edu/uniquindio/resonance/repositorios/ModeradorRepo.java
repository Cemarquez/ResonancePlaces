package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Moderador;
import org.dom4j.rule.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad moderador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface ModeradorRepo extends JpaRepository<Moderador,String> {

    @Query("select m from Moderador m")
    List<Moderador> listarModeradores();

    @Query("select m from Moderador m where m.nickname = ?1")
    Moderador buscarModPorNickname(String nickname);

    @Query("select m from Moderador m where m.email = ?1")
    Moderador buscarModPorEmail(String email);

    /*
    @Query("select l from Moderador m, IN (m.lugares)  l where m.nickname = ?1")
    List<Lugar> obtenerLugaresAutorizados(String nickname);

    @Query("select l from Moderador m, IN (m.lugaresRechazados) l where m.nickname = ?1 ")
    List<Lugar> obtenerLugaresRechazados(String nickname);
    */


    @Query("select l from Lugar l join Moderador m  ON l.moderador.nickname = m.nickname where m.nickname=?1 and l.estado= true and l.rechazado= false")
    List<Lugar> obtenerLugaresAutorizados(String nickname);

    @Query("select l from Lugar l join Moderador m  ON l.moderador.nickname = m.nickname where m.nickname=?1 and l.estado= true and l.rechazado= true")
    List<Lugar> obtenerLugaresRechazados(String nickname);




    @Query("select m from Moderador m where ?1 MEMBER OF m.lugares")
    boolean esLugarModerador(int idLugar);

    @Query("select l from Lugar l where l.estado= false  ")
    List <Lugar> obtenerLugaresSinAprobar();






    Moderador findByNicknameAndContrasena(String nicknmame, String contrasena);

    Moderador findAdministradorByEmailAndContrasena(String usuario,String contrasena);




}
