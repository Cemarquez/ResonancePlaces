package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Favorito;
import co.edu.uniquindio.resonance.repositorios.FavoritoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion de FavoritonServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
public class FavoritoServicioImpl implements FavoritoServicio {

    @Autowired
    private final FavoritoRepo favoritoRepo;

    public FavoritoServicioImpl(FavoritoRepo favoritoRepo) {
        this.favoritoRepo = favoritoRepo;
    }

    /**
     * Método que registra un favorito en la lógica
     * @param favorito
     * @return
     * @throws Exception
     */
    @Override
    public Favorito registrarFavorito(Favorito favorito) throws  Exception {

       Optional<Favorito> buscado = favoritoRepo.findById(favorito.getCodigo());

        if (buscado.isPresent()){
            throw new Exception("El elemento ya esta en favoritos");

        }


        if (favorito.getUsuario()==null){
            throw new Exception("El usuario no es válido");
        }

        if (favorito.getLugar()==null){
            throw new Exception("El lugar no es válido");
        }
        favoritoRepo.save(favorito);

        return favorito;
    }

    /**
     * Método que actualiza un favorito en la lógica
     * @param favorito
     * @return
     * @throws Exception
     */
    @Override
    public Favorito actualizarFavorito(Favorito favorito) throws  Exception{
        if (favorito.getUsuario()==null){
            throw new Exception("El usuario no es válido");
        }

        if (favorito.getLugar()==null){
            throw new Exception("El lugar no es válido");
        }
        Favorito favoritoGuardado = favoritoRepo.save(favorito);
        return favoritoGuardado;
    }

    /**
     * Método que elimina un favorito en la lógica
     * @param favorito
     * @throws Exception
     */
    @Override
    public void eliminarFavorito(Favorito favorito) throws  Exception {
        if (favoritoRepo.findById(favorito.getCodigo())==null){
            throw new Exception("Favorito seleccionado es inválido");
        }

        if (favorito.getUsuario()==null){
            throw new Exception("El usuario no es válido");
        }

        if (favorito.getLugar()==null){
            throw new Exception("El lugar no es válido");
        }

        favoritoRepo.delete(favorito);
    }

    /**
     * Método que lista todos los favoritos en la lógica
     * @return
     */
    @Override
    public List<Favorito> listarFavoritos() {
        return favoritoRepo.findAll();
    }
}
