package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;

import java.util.List;

public interface LugarServicio {

    Lugar registrarLugar(Lugar lugar) throws Exception;
    void eliminarLugar(Lugar lugar) throws Exception;
    Lugar actualizarLugar(Lugar lugar) throws Exception;
    List<Lugar> listarLugares();
    List<Lugar> buscarLugares(String parametro);
    Lugar obtenerLugar(Integer codigo);
    List<Horario>listarHorarios(Integer codigo);
    List<Calificacion>listarCalificaciones(Integer codigo);
    Calificacion crearCalificacion(Calificacion c);


}
