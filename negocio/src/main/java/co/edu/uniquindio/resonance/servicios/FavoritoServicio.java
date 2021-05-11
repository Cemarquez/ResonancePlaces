package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Favorito;

import java.util.List;

/**
 * Interface de FavoritoServicio para Favorito
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
public interface FavoritoServicio {

    Favorito registrarFavorito(Favorito favorito) throws  Exception;
    Favorito actualizarFavorito(Favorito favorito) throws  Exception;
    void eliminarFavorito(Favorito favorito) throws  Exception;
    List<Favorito> listarFavoritos();


}
