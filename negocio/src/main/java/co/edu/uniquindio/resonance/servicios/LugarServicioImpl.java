package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LugarServicioImpl implements LugarServicio{

    @Autowired
    private LugarRepo lugarRepo;

    @Override
    public Lugar registrarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> buscado = lugarRepo.findById(lugar.getCodigo());

        if(buscado.isPresent()){
            throw new Exception("Ya existe un lugar con ese codigo!");
        }

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
}
