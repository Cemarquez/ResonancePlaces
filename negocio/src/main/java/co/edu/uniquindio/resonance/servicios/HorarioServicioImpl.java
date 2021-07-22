package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.repositorios.HorarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de HorarioServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@Service
public class HorarioServicioImpl implements HorarioServicio{
    @Autowired
    private  final HorarioRepo horarioRepo;

    public HorarioServicioImpl(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }

    /**
     * Método que registra  horario en la lógica
     * @param horario
     * @return
     * @throws Exception
     */
    @Override
    public Horario registrarHorario(Horario horario) throws Exception {

        Optional<Horario> buscado = horarioRepo.findById(horario.getCodigo());

        if (buscado.isPresent()){
            throw  new Exception("El horario ya existe");
        }

        if (horario.getHoraInicio() ==  null ){
            throw new Exception("Ingrese hora de inicio valida");
        }

        if (horario.getHoraCierre() == null){
            throw new Exception("Ingrese hora de cierre valida");
        }

        if (horario.getDia().length()>50){
            throw new Exception("La descripcion no puede exceder los 50 caracteres");
        }

        if (horario.getLugar()==null){
            throw new Exception("Ingrese lugar valido ");
        }

        horarioRepo.save(horario);
        return horario;
    }

    /**
     * Método que actualiza horario en la lógica
     * @param horario
     * @return
     * @throws Exception
     */
    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {
        Optional<Horario> buscado = horarioRepo.findById(horario.getCodigo());

        if (!buscado.isPresent()){
            throw  new Exception("El horario a actualizar no existe");
        }

        if (horario.getHoraInicio() ==  null ){
            throw new Exception("Ingrese hora de inicio valida");
        }

        if (horario.getHoraCierre() == null){
            throw new Exception("Ingrese hora de cierre valida");
        }

        if (horario.getDia().length()>50){
            throw new Exception("La descripcion no puede exceder los 50 caracteres");
        }

        if (horario.getLugar()==null){
            throw new Exception("Ingrese lugar valido ");
        }

        horarioRepo.save(horario);
        return horario;
    }

    /**
     * Método que elimina horario en la lógica
     * @param horario
     * @throws Exception
     */
    @Override
    public void eliminarHorario(Horario horario) throws Exception {
        Optional<Horario> buscado = horarioRepo.findById(horario.getCodigo());

        if (!buscado.isPresent()){
            throw  new Exception("El horario no existe");
        }

        horarioRepo.delete(horario);

    }

    /**
     * Método que lista los horarios en la lógica
     * @return
     */
    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }
}
