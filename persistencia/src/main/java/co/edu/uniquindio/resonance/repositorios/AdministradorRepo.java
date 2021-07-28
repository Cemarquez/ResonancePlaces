package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase Repository para la entidad administrador
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {


    @Query("select ad from Administrador ad")
    List<Administrador> listarAdministradores();

    Administrador findByNicknameAndContrasena(String nicknmame,String contrasena);

    Administrador findAdministradorByEmailAndContrasena(String usuario,String contrasena);

    @Query("select a.moderadores from Administrador a where a.nickname=?1")
    List<Moderador> listarModeradores(String nicknameAdmin);

    /**
     * Genera reporte de la cantidad de lugares por categoria
     * @return listado de reportes con categoria y cantidad de lugares que tiene esa categoria
     */
    @Query("select new co.edu.uniquindio.resonance.repositorios.Reporte1DTO(l.categoria.nombre,count(l.codigo) )   from Lugar l GROUP BY l.categoria.nombre")
    List<Reporte1DTO>  generarReporte1();
    /**
     * Genera reporte de la cantidad de lugares por ciudad
     * @return listado de reportes con ciudad y cantidad de lugares que tiene esa ciudad
     */
    @Query("select new co.edu.uniquindio.resonance.repositorios.Reporte2DTO(l.ciudad.nombre, count (l.codigo)) from Lugar l GROUP BY  l.ciudad.nombre")
    List<Reporte2DTO>  generarReporte2();

}
