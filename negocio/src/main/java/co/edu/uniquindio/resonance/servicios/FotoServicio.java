package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Foto;

import java.util.List;

public interface FotoServicio {

    Foto registrarFoto(Foto foto) throws Exception;
    void eliminarFoto(Foto foto) throws Exception;
    Foto actualizarFoto(Foto foto) throws Exception;
    List<Foto> listarFotos();
}
