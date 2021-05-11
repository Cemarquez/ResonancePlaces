package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.CalificacionRepo;
import co.edu.uniquindio.resonance.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion de CalificacionServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
public class CalificacionServicioImpl implements CalificacionServicio {

    @Autowired
    private final CalificacionRepo calificacionRepo;


    public CalificacionServicioImpl(CalificacionRepo calificacionRepo) {
        this.calificacionRepo = calificacionRepo;
    }


    /**
     * Método que obtiene lista de calificaciones mayor al valor de la calificacion ingresada en parametro
     * @param calificacion
     * @return
     */
    @Override
    public List<Calificacion> obtenerListaPorCalificacion(double calificacion) {


        return calificacionRepo.obtenerListaPorCalificacion(calificacion);
    }

    /**
     * Método que registra calificaciones de un lugar en la logica
     * @param calificacion
     * @return
     */
    @Override
    public Calificacion registrarCalificacion(Calificacion calificacion) throws Exception {

        if (calificacion.getUsuario()==null){
                throw new Exception("El usuario no existe");
        }

        if (calificacion.getLugar()==null){
            throw new Exception("El lugar no existe");
        }

        if (calificacion.getMensaje().length()>255){
            throw new Exception("El mensaje no puede exceder los 255 caracteres");


        }

        if (calificacion.getTitulo().length()>255){
            throw new Exception("El titulo no puede exceder los 255 caracteres");
        }

        Calificacion calificacionRegistrada = calificacionRepo.save(calificacion);


        return calificacionRegistrada;
    }

    /**
     * Método que elimina calificaciones  en la logica
     * @param calificacion
     */
    @Override
    public void eliminarCalificacion(Calificacion calificacion) throws Exception {
        Optional<Calificacion> buscado = calificacionRepo.findById(calificacion.getCodigo());


        if (!buscado.isPresent()){
            throw new Exception("La calificacion no existe");
        }

        calificacionRepo.delete(calificacion);
    }

    /**
     * Método que actualiza calificaciones en la logica
     * @param calificacion
     * @return
     */
    @Override
    public Calificacion actualizarCalificacion(Calificacion calificacion) throws Exception {
        Optional<Calificacion> buscado = calificacionRepo.findById(calificacion.getCodigo());

        if (!buscado.isPresent()){
            throw new Exception("La calificacion no existe");
        }
        if (calificacion.getMensaje().length()>255){
            throw new Exception("El mensaje no puede exceder los 255 caracteres");


        }

        if (calificacion.getTitulo().length()>255){
            throw new Exception("El titulo no puede exceder los 255 caracteres");
        }

        Calificacion calificacionActualizada = calificacionRepo.save(calificacion);

        return calificacionActualizada;
    }

    /**
     * Método que obtiene lista de todas las calificaciones realizadas
     * @return
     */
    @Override
    public List<Calificacion> listarCalificaciones() {
        return calificacionRepo.findAll();
    }
}
