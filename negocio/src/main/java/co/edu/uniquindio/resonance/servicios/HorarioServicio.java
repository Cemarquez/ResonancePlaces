package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Horario;

import java.util.List;

/**
 * Interface de HorarioServicio para Horario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
public interface HorarioServicio {

    Horario registrarHorario(Horario horario) throws Exception;
    Horario actualizarHorario(Horario horario) throws Exception;
    void eliminarHorario(Horario horario) throws  Exception;
    List<Horario> listarHorarios();
}
