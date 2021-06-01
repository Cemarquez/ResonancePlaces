package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Lugar;

import java.util.List;

public interface LugarServicio {

    Lugar registrarLugar(Lugar lugar) throws Exception;
    void eliminarLugar(Lugar lugar) throws Exception;
    Lugar actualizarLugar(Lugar lugar) throws Exception;
    List<Lugar> listarLugares();

}
