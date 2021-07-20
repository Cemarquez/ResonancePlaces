package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Foto;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.CalificacionRepo;
import co.edu.uniquindio.resonance.repositorios.FotoRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LugarServicioImpl implements LugarServicio{

    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private FotoRepo fotoRepo;

    @Autowired
    private CalificacionRepo calificacionRepo;

    @Override
    public Lugar registrarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());

        if(buscado.isPresent()){
            throw new Exception("Ya existe un lugar con ese codigo!");
        }

        lugar.setEstado(false);

        return lugarRepo.save(lugar);
    }

    @Override
    public void eliminarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());

        if(!buscado.isPresent()){
            throw new Exception("No existe un lugar con ese codigo!");
        }

        lugarRepo.delete(lugar);
    }

    @Override
    public Lugar actualizarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());

        if(!buscado.isPresent()){
            throw new Exception("No existe un lugar con ese codigo!");
        }

        return lugarRepo.save(lugar);
    }

    @Override
    public List<Lugar> listarLugares() {
         return lugarRepo.findAll();
    }

    @Override
    public List<Lugar> buscarLugares(String parametro) {

        return lugarRepo.buscarLugares(parametro);
    }

    @Override
    public Lugar obtenerLugar(Integer codigo){

        return lugarRepo.findById(codigo).get();


    }

    @Override
    public List<Horario> listarHorarios(Integer codigo) {
        return lugarRepo.listarHorarios(codigo);
    }

    @Override
    public List<Calificacion> listarCalificaciones(Integer codigo) {
        return lugarRepo.listarCalificaciones(codigo);
    }

    @Override
    public Calificacion crearCalificacion(Calificacion c)
    {
        c.setFecha(new Date());
        return calificacionRepo.save(c);
    }

    @Override
    public Foto registrarFoto(Foto foto) {
        return fotoRepo.save(foto);
    }
}
