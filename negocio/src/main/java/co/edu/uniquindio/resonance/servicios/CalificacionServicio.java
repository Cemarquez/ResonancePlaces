package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Calificacion;

import java.util.List;

/**
 * Interface de CalificacionServicio para Calificacion
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
public interface CalificacionServicio {

    List<Calificacion> obtenerListaPorCalificacion(double calificacion) ;

    Calificacion registrarCalificacion(Calificacion calificacion) throws Exception;
    void eliminarCalificacion(Calificacion calificacion) throws Exception;
    Calificacion actualizarCalificacion(Calificacion calificacion) throws Exception;
    List<Calificacion> listarCalificaciones();
    Calificacion obtenerCalificacion(Integer id);




}
